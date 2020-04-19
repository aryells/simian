package com.mercadolivre.simian.service;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mercadolivre.simian.domain.Human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.mercadolivre.simian.domain.dto.HumanDTO;
import com.mercadolivre.simian.domain.dto.StatsDTO;
import com.mercadolivre.simian.exception.NotSimianException;
import com.mercadolivre.simian.exception.NotValidDNAException;
import com.mercadolivre.simian.repository.HumanRepository;

@Service
@EnableAsync
public class HumanService {

    @Autowired
    private HumanRepository humanRepository;

    @Value("${default.regex.mutantDNA}")
    private String[] mutantDNA;

    @Value("${default.regex.humanDNA}")
    private String humanDNA;

    public StatsDTO getStats() {
        long countHumans = humanRepository.countByIsSimian(false);
        long countMutants = humanRepository.countByIsSimian(true);
        final StatsDTO.Builder builder = StatsDTO.builder().countMutantDna(countMutants)
                .countHumanDna(countHumans);
        if(countHumans == 0) {
            return builder.ratio(0D).build();
        }
        final BigDecimal ratio = BigDecimal.valueOf(countMutants).divide(BigDecimal.valueOf(countHumans), 2, BigDecimal.ROUND_UP);
        return builder.ratio(ratio.doubleValue()).build();
    }

    public HumanDTO save(HumanDTO humanDTO) throws NotValidDNAException, NotSimianException {
        String md5 = null;
        try {
            md5 = HumanDTO.getMD5Hash(humanDTO);
            validateSimian(humanDTO.getDna().stream().toArray(String[]::new));
            humanDTO.setIsSimian(true);
            saveOnlyNewDnaAsync(humanDTO, md5);
            return humanDTO;
        } catch (NotSimianException e) {
            humanDTO.setIsSimian(false);
            saveOnlyNewDnaAsync(humanDTO, md5);
            throw e;
        } catch (Exception e) {
            throw new NotValidDNAException();
        }
    }

    private void validateSimian(String[] dna) throws NotValidDNAException, NotSimianException {
        validateDNA(dna);
        int regexSeq = 0;
        for (String seq : dna) {
            if (runTest(mutantDNA[regexSeq], seq) != 1) throw new NotSimianException();
            regexSeq++;
        }
    }

    private void validateDNA(String[] dna) throws NotValidDNAException {
        if (dna.length != 6) throw new NotValidDNAException();
        for (String seq : dna) {
            if (runTest(humanDNA, seq) != 1) throw new NotValidDNAException();
        }
    }

    private int runTest(String regex, String text) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        int matches = 0;
        while (matcher.find()) {
            matches++;
        }
        return matches;
    }

    private void saveOnlyNewDnaAsync(HumanDTO humanDTO, String md5) {
        supplyAsync(() ->
                humanRepository.findByMd5(md5).orElseGet(() ->humanRepository.save(
                        Human.builder().dna(humanDTO.getDna()).isSimian(humanDTO.getIsSimian()).md5(md5).build()))
        );
    }
}
