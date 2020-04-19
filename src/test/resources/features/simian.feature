Feature: Validate and save simian
  Scenario: simian: client makes call to POST /simian
    Given validate and save new simian
      | DNA|
      | CTGAGA, CTAGGC, TATTGT, AGAGGG, CCCCTA, TCACTG|
    When the client calls /simian
    Then the client receives response status code of 200

  Scenario: human: client makes call to POST /simian
    Given validate and save new simian
      | DNA|
      | ATGCGA, CAGTGC, TTATGT, AGAAGG, TCACTG, TCACTG|
    When the client calls /simian
    Then the client receives response status code of 403

  Scenario: no human - DNA size < 6: client makes call to POST /simian
    Given validate and save new simian
      | DNA|
      | ATGCGA, CAGTGC, TTATGT, AGAAGG|
    When the client calls /simian
    Then the client receives response status code of 400

  Scenario: no human - DNA diferent group [A,T,C,G]: client makes call to POST /simian
    Given validate and save new simian
      | DNA|
      | ATGCGA, CAGTGC, TTATGT, KGAAGG, CCCCTA, TCACTG|
    When the client calls /simian
    Then the client receives response status code of 400

  Scenario: generate md5
    Given dna invalid

  Scenario: batch - insert humans
    Given save humans
      |dna|isSimian|md5|
      |[TCGGGT, TCAGGC, TATTGT, AGAGGG, TCCCTA, TCAACG]| false|339045562388224946946524414239130319005|
      |[CTGAGA, ACAGGC, TATTGT, AGAGGG, ACCCTA, ACAATC]| false| 81474863993071795046691324998648848144|
      |[ACGAGT, ACAGGC, TATTGT, AGAGGG, ACTCTA, TCAATG]| false|206183890776990020878702029205234831269|
      |[ACGAGA, CTAGGC, TATTGT, AGAGGG, ACCCTA, ACAATG]| false|101888865396258540961859082847106445167|
      |[TCGGGA, CTAGGC, TATTGT, AGAGGG, TCCCTA, TCAACG]| false|235606547114054827679326381749742286838|
      |[CTGAGA, TAAGGC, TATTGT, AGAGGG, TACCTA, TCAAAG]| false| 83596401462800339055239353997257675014|
      |[CTGAGA, CTAGGC, TATTGT, AGAGGG, TACCTA, TCAAAG]| false|208532244577463563654862574223576980347|
      |[CTGAGA, CTAGGC, TATTGT, AGAGGG, ACTCTA, TCAATG]| false| 22410621693834598354502053721964871716|
      |[CTGAGA, CTAGGC, TATTGT, AGAGGG, CCCCTA, TCACAG]| true	|312363015241322881294918712800339134208|
      |[CTGAGA, CTAGGC, TATTGT, AGAGGG, TTCCTA, TCAATG]| false|250834894583600232202331525204829883058|
      |[ACGAGT, ACAGGC, TATTGT, AGAGGG, ACCCTA, ACAATA]| false| 54686125157073113650211223557919166062|
      |[ACGAGT, ACAGGC, TATTGT, AGAGGG, ACACTA, TCAAAG]| false| 39551246692336017659052189509700004426|
      |[ACGAGA, CTAGGC, TATTGT, AGAGGG, ACCCTA, ACAATT]| false|166490553772027523097424179563854714032|
      |[ACGAGA, ACAGGC, TATTGT, AGAGGG, ACACTA, TCAAAG]| false|243859746007528868998278689354081340116|
      |[ACGAGA, ACAGGC, TATTGT, AGAGGG, ACCCTA, ACAATG]| false|338129373957810545420877752231398961194|
      |[CTGAGA, CTAGGC, TATTGT, AGAGGG, CCCCTA, TCACTC]| true	| 74028589435885382502241819639362826184|
      |[TCGGGA, TCAGGC, TATTGT, AGAGGG, TCCCTA, TCAACG]| false|102316830199458458907983460921152178778|
      |[ACGAGT, ACAGGC, TATTGT, AGAGGG, ACCCTA, ACAATG]| false|232757124960776644258109776981798148517|
      |[ACGAGA, ACAGGC, TATTGT, AGAGGG, ACCCTA, ACAATA]| false|2 9489963057051861037625533317013669862|
      |[TTGAGA, TTAGGC, TATTGT, AGAGGG, TTCCTA, TCAATG]| false|189350197288465712316132213015508973183|
      |[CTGAGA, CTAGGC, TATTGT, AGAGGG, TGCCTA, TCAAGG]| false|237631230740681712355784553650358364294|
      |[TAGAGA, CTAGGC, TATTGT, AGAGGG, TACCTA, TCAAAG]| false|129456848113704855844519240105587976377|
      |[CTGAGA, ACAGGC, TATTGT, AGAGGG, ACTCTA, TCAATG]| false|116395336872258572707566419096855196167|
      |[TAGAGA, TAAGGC, TATTGT, AGAGGG, TACCTA, TCAAAG]| false|189794639773410826128547599146999534285|
      |[CTGAGA, CTAGGC, TATTGT, AGAGGG, ACCCTA, ACAATG]| false|207841007406540159912840607944141107834|
      |[TAGAGT, TAAGGC, TATTGT, AGAGGG, TACCTA, TCAAAG]| false|135251740560829898389271733628777613758|
      |[CTGAGA, GTAGGC, TATTGT, AGAGGG, CCCCTA, TCACTG]| true	| 97742449020545275913256275621059821765|
      |[CTGAGA, TGAGGC, TATTGT, AGAGGG, TGCCTA, TCAAGG]| false|173214370065850315027916721251031589153|
      |[ACGAGA, ACAGGC, TATTGT, AGAGGG, ACCCTA, ACAATT]| false|338622231621949844881754954102111780960|
      |[CTGAGA, ACAGGC, TATTGT, AGAGGG, ACACTA, TCAAAG]| false|319359620547281046889816900185082045514|
      |[CTGAGA, ACAGGC, TATTGT, AGAGGG, ACCCTA, ACAATA]| false|229250132358295944308111878436726139653|
      |[CTGAGA, ACAGGC, TATTGT, AGAGGG, ACCCTA, ACAATT]| false|254420977593755743219779448092768614089|
      |[ACGAGA, CTAGGC, TATTGT, AGAGGG, ACTCTA, TCAATG]| false|143190359136670915002364653952848480312|
      |[CTGGGA, TCAGGC, TATTGT, AGAGGG, TCCCTA, TCAACG]| false| 67978396151687882684075223836331366003|
      |[CTGAGA, CTAGGC, TATTGT, AGAGGG, ACCCTA, ACAATT]| false|303056862046068260049441047761776736825|
      |[CTGAGA, TTAGGC, TATTGT, AGAGGG, TTCCTA, TCAATG]| false|238902490492291909237665141149683443523|
      |[CTGAGA, CTAGGC, TATTGT, AGAGGG, CCCCTA, TCACTT]| true	|211844923363167488735216050772841870074|
      |[TGGAGA, CTAGGC, TATTGT, AGAGGG, TGCCTA, TCAAGG]| false|269078699207228691400762097236053021833|
      |[CTGAGA, CTAGGC, TATTGT, AGAGGG, CCCCTA, TCACTA]| true	|  3367165298202266951493059930923182867|
      |[CTGAGA, CTAGGC, TATTGT, AGAGGG, CCCCTA, TCACGG]| true	|169000456599112796792309950273645579296|
      |[TTGAGA, CTAGGC, TATTGT, AGAGGG, TTCCTA, TCAATG]| false|323966525888254028400335094268944339483|
      |[CAGAGA, CTAGGC, TATTGT, AGAGGG, CCCCTA, TCACAG]| true	|302352717117017480659630721949461099466|
      |[ACGAGA, CTAGGC, TATTGT, AGAGGG, ACACTA, TCAAAG]| false|129661848668212948094097141210879848461|
      |[CTGAGA, CTAGGC, TATTGT, AGAGGG, CCCCTA, TCACCG]| true	| 97087676119877987368180061976778445988|
      |[CTGAGA, CTAGGC, TATTGT, AGAGGG, ACCCTA, ACAATA]| false|308862465147205429476721687664258825611|
      |[GTGAGA, CTAGGC, TATTGT, AGAGGG, CCCCTA, TCACTG]| true	|102197538111310612769146084947487613836|
      |[ACGAGA, ACAGGC, TATTGT, AGAGGG, ACCCTA, ACAATC]| false| 76232674803549653364233923459681375064|
      |[ACGAGT, ACAGGC, TATTGT, AGAGGG, ACCCTA, ACAATT]| false|307529285867158877337647695268952425707|
      |[CTGAGA, ACAGGC, TATTGT, AGAGGG, ACCCTA, ACAATG]| false| 54755049634291334203639256352578282066|
      |[TGGAGT, TGAGGC, TATTGT, AGAGGG, TGCCTA, TCAAGG]| false|338267669130658292674623858662183485766|
      |[TGGAGA, TGAGGC, TATTGT, AGAGGG, TGCCTA, TCAAGG]| false|226942491588738979330768403273451094215|
      |[CTGAGA, CTAGGC, TATTGT, AGAGGG, ACACTA, TCAAAG]| false|139365876855931151239436562484593960969|
      |[ACGAGA, ACAGGC, TATTGT, AGAGGG, ACTCTA, TCAATG]| false| 98741286615733022520882675354576003809|
      |[TTGAGT, TTAGGC, TATTGT, AGAGGG, TTCCTA, TCAATG]| false|258106124689992221534899801868931243304|
      |[CTGAGA, CTAGGC, TATTGT, AGAGGG, ACCCTA, ACAATC]| false|284062744460355956939282508008749432236|
      |[ACGAGA, CTAGGC, TATTGT, AGAGGG, ACCCTA, ACAATC]| false|  5922666722748038965893883959713991341|
      |[CTGGGA, CTAGGC, TATTGT, AGAGGG, TCCCTA, TCAACG]| false|144308139758368244745056959300728249255|
      |[ACGAGA, CTAGGC, TATTGT, AGAGGG, ACCCTA, ACAATA]| false|122719996773892483840334060236080679525|
    When the client calls /simian/stats
    Then ratio calculator
    
  Scenario: batch - drop humans
    Given drop humans
    When the client calls /simian/stats
    Then ratio calculator

  Scenario: simian not saved: client makes call to POST /simian
    Given exist one item in datastore
    And validate and save new simian
      | DNA|
      | CTGAGA, CTAGGC, TATTGT, AGAGGG, CCCCTA, TCACAG|
    When the client calls /simian
    Then the client receives response status code of 200
    And total simians is equal to 1