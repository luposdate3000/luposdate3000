#!/bin/bash
queries1=(
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fn1%20%3Fn2%20where%20%7B%20%3Fa1%20%3CactedIn%3E%20%3Fm1%20.%20%3Fa2%20%3CactedIn%3E%20%3Fm1%20.%20%3Fa1%20%3ChasGivenName%3E%20%3Fn1%20.%20%3Fa2%20%3ChasGivenName%3E%20%3Fn2%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20%20select%20distinct%20%3Fn1%20%3Fn2%20where%20%7B%20%3Fp1%20%3ChasFamilyName%3E%20%3Fn1%20.%20%3Fp2%20%3ChasFamilyName%3E%20%3Fn2%20.%20%3Fp1%20a%20%3Cwordnet_scientist_110560637%3E%20.%20%3Fp2%20a%20%3Cwordnet_scientist_110560637%3E%20.%20%3Fc1%20a%20%3Cwordnet_site_108651247%3E%20.%20%3Fp1%20%3Fx1%20%3Fc1%20.%20%3Fp2%20%3Fy1%20%3Fc1%20.%20filter%20%28%3Fp1%20%21%3D%20%3Fp2%29%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fgn%20%3Ffn%20where%20%7B%20%3Fp1%20%3CbornIn%3E%20%3Fc1%20.%20%3Fa1%20%3CbornIn%3E%20%3Fc2%20.%20%3Fp1%20%3ChasAcademicAdvisor%3E%20%3Fa1%20.%20%3Fp1%20%3ChasFamilyName%3E%20%3Ffn%20.%20%3Fp1%20%3ChasGivenName%3E%20%3Fgn%20.%20%3Fp1%20a%20%3Cwordnet_scientist_110560637%3E%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fn1%20where%20%7B%20%3Fa1%20%3CactedIn%3E%20%3Fm1%20.%20%3Fa1%20%3Cdirected%3E%20%3Fm2%20.%20%3Fa1%20%3ChasGivenName%3E%20%3Fn1%20.%20%3Fa1%20%3ClivesIn%3E%20%3Fc1%20.%20%3Fa1%20a%20%3Cwordnet_actor_109765278%3E%20.%20%3Fm1%20a%20%3Cwordnet_movie_106613686%3E%20.%20%3Fm2%20a%20%3Cwordnet_movie_106613686%3E%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20distinct%20%3Fn1%20%3Fn2%20where%20%7B%20%3Fa2%20%3CactedIn%3E%20%3Fm1%20.%20%3Fa1%20%3CactedIn%3E%20%3Fm1%20.%20%3Fa1%20%3ChasGivenName%3E%20%3Fn1%20.%20%3Fa2%20%3ChasGivenName%3E%20%3Fn2%20.%20%3Fa1%20%3ClivesIn%3E%20%3Fc1%20.%20%3Fa2%20%3ClivesIn%3E%20%3Fc2%20.%20filter%20%28%3Fa1%20%21%3D%20%3Fa2%29%20.%20filter%20%28%3Fc1%20%3D%20%3Fc2%29%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20distinct%20%3Fn1%20%3Fn2%20where%20%7B%20%3Fp1%20%3CbornIn%3E%20%3Fc1%20.%20%3Fp2%20%3CbornIn%3E%20%3Fc1%20.%20%3Fp1%20%3ChasFamilyName%3E%20%3Fn1%20.%20%3Fp2%20%3ChasFamilyName%3E%20%3Fn2%20.%20%3Fp1%20%3ChasWonPrize%3E%20%3Faw%20.%20%3Fp2%20%3ChasWonPrize%3E%20%3Faw.%20%3Fp1%20a%20%3Cwordnet_scientist_110560637%3E%20.%20%3Fp2%20a%20%3Cwordnet_scientist_110560637%3E%20.%20filter%20%28%3Fp1%20%21%3D%20%3Fp2%29%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Ffn%20%3Fgn%20where%20%7B%20%3Fp1%20%3CbornIn%3E%20%3Fc1%20.%20%3Fa1%20%3CbornIn%3E%20%3Fc2%20.%20%3Fp1%20%3ChasAcademicAdvisor%3E%20%3Fa1%20.%20%3Fp1%20%3ChasFamilyName%3E%20%3Ffn%20.%20%3Fp1%20%3ChasGivenName%3E%20%3Fgn%20.%20%3Fp1%20a%20%3Cwordnet_scientist_110560637%3E%20.%20%3Fc2%20%3ClocatedIn%3E%20%3CGermany%3E%20.%20%3Fc1%20%3ClocatedIn%3E%20%3CSwitzerland%3E%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fgn%20%3Ffn%20where%20%7B%20%3Fp1%20%3CbornIn%3E%20%3Fc1%20.%20%3Fa1%20%3CbornIn%3E%20%3Fc1%20.%20%3Fp1%20%3ChasAcademicAdvisor%3E%20%3Fa1%20.%20%3Fp1%20%3ChasFamilyName%3E%20%3Ffn%20.%20%3Fp1%20%3ChasGivenName%3E%20%3Fgn%20.%20%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fgn%20%3Ffn%20where%20%7B%20%3Fp1%20%3ChasAcademicAdvisor%3E%20%3Fa1%20.%20%3Fp1%20%3ChasFamilyName%3E%20%3Ffn%20.%20%3Fp1%20%3ChasGivenName%3E%20%3Fgn%20.%20%3Fp1%20%3CisMarriedTo%3E%20%3Fp2%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fn1%20%3Fn2%20where%20%7B%20%3Fp1%20%3CbornIn%3E%20%3Fc1%20.%20%3Fp2%20%3CbornIn%3E%20%3Fc1%20.%20%3Fp1%20%3ChasGivenName%3E%20%3Fn1%20.%20%3Fp2%20%3ChasGivenName%3E%20%3Fn2%20.%20%3Fp1%20%3CisMarriedTo%3E%20%3Fp2%20.%20%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fx%20where%20%7B%20%3Fx%20%3ClivesIn%3E%20%3CAthens%3E%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fy%20%3Fu%20where%20%7B%20%3CAlbert_Einstein%3E%20%3CgraduatedFrom%3E%20%3Fu%20.%20%3Fy%20%3CgraduatedFrom%3E%20%3Fu%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fn1%20where%20%7B%20%3Fa1%20%3CactedIn%3E%20%3Fm1%20.%20%3Fa1%20%3Cdirected%3E%20%3Fm2%20.%20%3Fa1%20%3ChasGivenName%3E%20%3Fn1%20.%20%3Fa1%20%3ClivesIn%3E%20%3Fc1%20.%20%3Fc1%20%3ClocatedIn%3E%20%3Fs%20.%20%3Fm1%20a%20%3Cwikicategory_2000_films%3E%20.%20%3Fm2%20a%20%3Cwikicategory_2000_films%3E%20.%20%3Fa1%20a%20%3Cwordnet_actor_109765278%3E%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20distinct%20%3Fn1%20%3Fn2%20where%20%7B%20%3Fa1%20%3CactedIn%3E%20%3Fm1%20.%20%3Fa2%20%3CactedIn%3E%20%3Fm1.%20%3Fa1%20%3ChasGivenName%3E%20%3Fn1%20.%20%3Fa2%20%3ChasGivenName%3E%20%3Fn2%20.%20%3Fa1%20%3ClivesIn%3E%20%3Fc1%20.%20%3Fa2%20%3ClivesIn%3E%20%3Fc1%20.%20filter%20%28%3Fa1%20%21%3D%20%3Fa2%29%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fn1%20%3Fn2%20where%20%7B%20%3Fp1%20%3CbornIn%3E%20%3Fc1%20.%20%3Fp2%20%3CbornIn%3E%20%3Fc1%20.%20%3Fp1%20%3ChasGivenName%3E%20%3Fn1%20.%20%3Fp2%20%3ChasGivenName%3E%20%3Fn2%20.%20%3Fp1%20%3CisMarriedTo%3E%20%3Fp2%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20PREFIX%20rdfs%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%20select%20distinct%20%3Fn1%20where%20%7B%20%3Fp1%20%3ChasGivenName%3E%20%3Fn1%20.%20%3Fc2%20a%20%3Cwordnet_site_108651247%3E%20.%20%3Fc1%20a%20%3Cwordnet_village_108672738%3E%20.%20%3Fc1%20rdfs%3Alabel%20%27London%27%20.%20%3Fc2%20rdfs%3Alabel%20%27Paris%27%20.%20%3Fp1%20%3Fx1%20%3Fc1%20.%20%3Fp1%20%3Fy1%20%3Fc2%20.%20%7D"
# very slow "BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20distinct%20%3Fn1%20%3Fn2%20where%20%7B%20%3Fp1%20%3ChasFamilyName%3E%20%3Fn1%20.%20%3Fp2%20%3ChasFamilyName%3E%20%3Fn2%20.%20%3Fp1%20a%20%3Cwordnet_scientist_110560637%3E%20.%20%3Fp2%20a%20%3Cwordnet_scientist_110560637%3E%20.%20%3Fp1%20%3Fx1%20%3Fc1%20.%20%3Fp2%20%3Fy1%20%3Fc1%20.%20filter%20%28%3Fp1%20%21%3D%20%3Fp2%29%20.%20%7D"
)
queries2=(
# crash ?? "BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Ffn%20%3Fgn%20where%20%7B%20%3Fp1%20%3CbornIn%3E%20%3Fc1%20.%20%3Fa1%20%3CbornIn%3E%20%3Fc2%20.%20%3Fp1%20%3ChasAcademicAdvisor%3E%20%3Fa1%20.%20%3Fp1%20%3ChasFamilyName%3E%20%3Ffn%20.%20%3Fp1%20%3ChasGivenName%3E%20%3Fgn%20.%20%3Fp1%20a%20%3Cwordnet_scientist_110560637%3E%20.%20%3Fc2%20%3ClocatedIn%3E%20%3CGermany%3E%20.%20%3Fc1%20%3ClocatedIn%3E%20%3CSwitzerland%3E%20.%20%7D"
# more than 1 mins "BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fgn%20%3Ffn%20where%20%7B%20%3Fp1%20%3CbornIn%3E%20%3Fc1%20.%20%3Fa1%20%3CbornIn%3E%20%3Fc1%20.%20%3Fp1%20%3ChasAcademicAdvisor%3E%20%3Fa1%20.%20%3Fp1%20%3ChasFamilyName%3E%20%3Ffn%20.%20%3Fp1%20%3ChasGivenName%3E%20%3Fgn%20.%20%20%7D"
# crash "BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fn1%20%3Fn2%20where%20%7B%20%3Fp1%20%3CbornIn%3E%20%3Fc1%20.%20%3Fp2%20%3CbornIn%3E%20%3Fc1%20.%20%3Fp1%20%3ChasGivenName%3E%20%3Fn1%20.%20%3Fp2%20%3ChasGivenName%3E%20%3Fn2%20.%20%3Fp1%20%3CisMarriedTo%3E%20%3Fp2%20.%20%20%7D"
# crash "BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fy%20%3Fu%20where%20%7B%20%3CAlbert_Einstein%3E%20%3CgraduatedFrom%3E%20%3Fu%20.%20%3Fy%20%3CgraduatedFrom%3E%20%3Fu%20.%20%7D"
# more than 1 mins "BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fn1%20where%20%7B%20%3Fa1%20%3CactedIn%3E%20%3Fm1%20.%20%3Fa1%20%3Cdirected%3E%20%3Fm2%20.%20%3Fa1%20%3ChasGivenName%3E%20%3Fn1%20.%20%3Fa1%20%3ClivesIn%3E%20%3Fc1%20.%20%3Fc1%20%3ClocatedIn%3E%20%3Fs%20.%20%3Fm1%20a%20%3Cwikicategory_2000_films%3E%20.%20%3Fm2%20a%20%3Cwikicategory_2000_films%3E%20.%20%3Fa1%20a%20%3Cwordnet_actor_109765278%3E%20.%20%7D"
# crash "BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fn1%20%3Fn2%20where%20%7B%20%3Fp1%20%3CbornIn%3E%20%3Fc1%20.%20%3Fp2%20%3CbornIn%3E%20%3Fc1%20.%20%3Fp1%20%3ChasGivenName%3E%20%3Fn1%20.%20%3Fp2%20%3ChasGivenName%3E%20%3Fn2%20.%20%3Fp1%20%3CisMarriedTo%3E%20%3Fp2%20.%20%7D"
# more than 1 mins "BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fn1%20where%20%7B%20%3Fa1%20%3CactedIn%3E%20%3Fm1%20.%20%3Fa1%20%3Cdirected%3E%20%3Fm2%20.%20%3Fa1%20%3ChasGivenName%3E%20%3Fn1%20.%20%3Fa1%20%3ClivesIn%3E%20%3Fc1%20.%20%3Fa1%20a%20%3Cwordnet_actor_109765278%3E%20.%20%3Fm1%20a%20%3Cwordnet_movie_106613686%3E%20.%20%3Fm2%20a%20%3Cwordnet_movie_106613686%3E%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fx%20where%20%7B%20%3Fx%20%3ClivesIn%3E%20%3CAthens%3E%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20%20select%20distinct%20%3Fn1%20%3Fn2%20where%20%7B%20%3Fp1%20%3ChasFamilyName%3E%20%3Fn1%20.%20%3Fp2%20%3ChasFamilyName%3E%20%3Fn2%20.%20%3Fp1%20a%20%3Cwordnet_scientist_110560637%3E%20.%20%3Fp2%20a%20%3Cwordnet_scientist_110560637%3E%20.%20%3Fc1%20a%20%3Cwordnet_site_108651247%3E%20.%20%3Fp1%20%3Fx1%20%3Fc1%20.%20%3Fp2%20%3Fy1%20%3Fc1%20.%20filter%20%28%3Fp1%20%21%3D%20%3Fp2%29%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20distinct%20%3Fn1%20%3Fn2%20where%20%7B%20%3Fa2%20%3CactedIn%3E%20%3Fm1%20.%20%3Fa1%20%3CactedIn%3E%20%3Fm1%20.%20%3Fa1%20%3ChasGivenName%3E%20%3Fn1%20.%20%3Fa2%20%3ChasGivenName%3E%20%3Fn2%20.%20%3Fa1%20%3ClivesIn%3E%20%3Fc1%20.%20%3Fa2%20%3ClivesIn%3E%20%3Fc2%20.%20filter%20%28%3Fa1%20%21%3D%20%3Fa2%29%20.%20filter%20%28%3Fc1%20%3D%20%3Fc2%29%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20distinct%20%3Fn1%20%3Fn2%20where%20%7B%20%3Fp1%20%3CbornIn%3E%20%3Fc1%20.%20%3Fp2%20%3CbornIn%3E%20%3Fc1%20.%20%3Fp1%20%3ChasFamilyName%3E%20%3Fn1%20.%20%3Fp2%20%3ChasFamilyName%3E%20%3Fn2%20.%20%3Fp1%20%3ChasWonPrize%3E%20%3Faw%20.%20%3Fp2%20%3ChasWonPrize%3E%20%3Faw.%20%3Fp1%20a%20%3Cwordnet_scientist_110560637%3E%20.%20%3Fp2%20a%20%3Cwordnet_scientist_110560637%3E%20.%20filter%20%28%3Fp1%20%21%3D%20%3Fp2%29%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20distinct%20%3Fn1%20%3Fn2%20where%20%7B%20%3Fa1%20%3CactedIn%3E%20%3Fm1%20.%20%3Fa2%20%3CactedIn%3E%20%3Fm1.%20%3Fa1%20%3ChasGivenName%3E%20%3Fn1%20.%20%3Fa2%20%3ChasGivenName%3E%20%3Fn2%20.%20%3Fa1%20%3ClivesIn%3E%20%3Fc1%20.%20%3Fa2%20%3ClivesIn%3E%20%3Fc1%20.%20filter%20%28%3Fa1%20%21%3D%20%3Fa2%29%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fn1%20%3Fn2%20where%20%7B%20%3Fa1%20%3CactedIn%3E%20%3Fm1%20.%20%3Fa2%20%3CactedIn%3E%20%3Fm1%20.%20%3Fa1%20%3ChasGivenName%3E%20%3Fn1%20.%20%3Fa2%20%3ChasGivenName%3E%20%3Fn2%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fgn%20%3Ffn%20where%20%7B%20%3Fp1%20%3ChasAcademicAdvisor%3E%20%3Fa1%20.%20%3Fp1%20%3ChasFamilyName%3E%20%3Ffn%20.%20%3Fp1%20%3ChasGivenName%3E%20%3Fgn%20.%20%3Fp1%20%3CisMarriedTo%3E%20%3Fp2%20.%20%7D"
# more than 10seconds "BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20PREFIX%20rdfs%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%20select%20distinct%20%3Fn1%20where%20%7B%20%3Fp1%20%3ChasGivenName%3E%20%3Fn1%20.%20%3Fc2%20a%20%3Cwordnet_site_108651247%3E%20.%20%3Fc1%20a%20%3Cwordnet_village_108672738%3E%20.%20%3Fc1%20rdfs%3Alabel%20%27London%27%20.%20%3Fc2%20rdfs%3Alabel%20%27Paris%27%20.%20%3Fp1%20%3Fx1%20%3Fc1%20.%20%3Fp1%20%3Fy1%20%3Fc2%20.%20%7D"
# very slow "BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20distinct%20%3Fn1%20%3Fn2%20where%20%7B%20%3Fp1%20%3ChasFamilyName%3E%20%3Fn1%20.%20%3Fp2%20%3ChasFamilyName%3E%20%3Fn2%20.%20%3Fp1%20a%20%3Cwordnet_scientist_110560637%3E%20.%20%3Fp2%20a%20%3Cwordnet_scientist_110560637%3E%20.%20%3Fp1%20%3Fx1%20%3Fc1%20.%20%3Fp2%20%3Fy1%20%3Fc1%20.%20filter%20%28%3Fp1%20%21%3D%20%3Fp2%29%20.%20%7D"
# more than 3 mins "BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fgn%20%3Ffn%20where%20%7B%20%3Fp1%20%3CbornIn%3E%20%3Fc1%20.%20%3Fa1%20%3CbornIn%3E%20%3Fc2%20.%20%3Fp1%20%3ChasAcademicAdvisor%3E%20%3Fa1%20.%20%3Fp1%20%3ChasFamilyName%3E%20%3Ffn%20.%20%3Fp1%20%3ChasGivenName%3E%20%3Fgn%20.%20%3Fp1%20a%20%3Cwordnet_scientist_110560637%3E%20.%20%7D"
)
queries3=(
# working "BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fn1%20%3Fn2%20where%20%7B%20%3Fa1%20%3CactedIn%3E%20%3Fm1%20.%20%3Fa2%20%3CactedIn%3E%20%3Fm1%20.%20%3Fa1%20%3ChasGivenName%3E%20%3Fn1%20.%20%3Fa2%20%3ChasGivenName%3E%20%3Fn2%20.%20%7D"
# working "BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20%20select%20distinct%20%3Fn1%20%3Fn2%20where%20%7B%20%3Fp1%20%3ChasFamilyName%3E%20%3Fn1%20.%20%3Fp2%20%3ChasFamilyName%3E%20%3Fn2%20.%20%3Fp1%20a%20%3Cwordnet_scientist_110560637%3E%20.%20%3Fp2%20a%20%3Cwordnet_scientist_110560637%3E%20.%20%3Fc1%20a%20%3Cwordnet_site_108651247%3E%20.%20%3Fp1%20%3Fx1%20%3Fc1%20.%20%3Fp2%20%3Fy1%20%3Fc1%20.%20filter%20%28%3Fp1%20%21%3D%20%3Fp2%29%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fgn%20%3Ffn%20where%20%7B%20%3Fp1%20%3CbornIn%3E%20%3Fc1%20.%20%3Fa1%20%3CbornIn%3E%20%3Fc2%20.%20%3Fp1%20%3ChasAcademicAdvisor%3E%20%3Fa1%20.%20%3Fp1%20%3ChasFamilyName%3E%20%3Ffn%20.%20%3Fp1%20%3ChasGivenName%3E%20%3Fgn%20.%20%3Fp1%20a%20%3Cwordnet_scientist_110560637%3E%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fn1%20where%20%7B%20%3Fa1%20%3CactedIn%3E%20%3Fm1%20.%20%3Fa1%20%3Cdirected%3E%20%3Fm2%20.%20%3Fa1%20%3ChasGivenName%3E%20%3Fn1%20.%20%3Fa1%20%3ClivesIn%3E%20%3Fc1%20.%20%3Fa1%20a%20%3Cwordnet_actor_109765278%3E%20.%20%3Fm1%20a%20%3Cwordnet_movie_106613686%3E%20.%20%3Fm2%20a%20%3Cwordnet_movie_106613686%3E%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20distinct%20%3Fn1%20%3Fn2%20where%20%7B%20%3Fa2%20%3CactedIn%3E%20%3Fm1%20.%20%3Fa1%20%3CactedIn%3E%20%3Fm1%20.%20%3Fa1%20%3ChasGivenName%3E%20%3Fn1%20.%20%3Fa2%20%3ChasGivenName%3E%20%3Fn2%20.%20%3Fa1%20%3ClivesIn%3E%20%3Fc1%20.%20%3Fa2%20%3ClivesIn%3E%20%3Fc2%20.%20filter%20%28%3Fa1%20%21%3D%20%3Fa2%29%20.%20filter%20%28%3Fc1%20%3D%20%3Fc2%29%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20distinct%20%3Fn1%20%3Fn2%20where%20%7B%20%3Fp1%20%3CbornIn%3E%20%3Fc1%20.%20%3Fp2%20%3CbornIn%3E%20%3Fc1%20.%20%3Fp1%20%3ChasFamilyName%3E%20%3Fn1%20.%20%3Fp2%20%3ChasFamilyName%3E%20%3Fn2%20.%20%3Fp1%20%3ChasWonPrize%3E%20%3Faw%20.%20%3Fp2%20%3ChasWonPrize%3E%20%3Faw.%20%3Fp1%20a%20%3Cwordnet_scientist_110560637%3E%20.%20%3Fp2%20a%20%3Cwordnet_scientist_110560637%3E%20.%20filter%20%28%3Fp1%20%21%3D%20%3Fp2%29%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Ffn%20%3Fgn%20where%20%7B%20%3Fp1%20%3CbornIn%3E%20%3Fc1%20.%20%3Fa1%20%3CbornIn%3E%20%3Fc2%20.%20%3Fp1%20%3ChasAcademicAdvisor%3E%20%3Fa1%20.%20%3Fp1%20%3ChasFamilyName%3E%20%3Ffn%20.%20%3Fp1%20%3ChasGivenName%3E%20%3Fgn%20.%20%3Fp1%20a%20%3Cwordnet_scientist_110560637%3E%20.%20%3Fc2%20%3ClocatedIn%3E%20%3CGermany%3E%20.%20%3Fc1%20%3ClocatedIn%3E%20%3CSwitzerland%3E%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fgn%20%3Ffn%20where%20%7B%20%3Fp1%20%3CbornIn%3E%20%3Fc1%20.%20%3Fa1%20%3CbornIn%3E%20%3Fc1%20.%20%3Fp1%20%3ChasAcademicAdvisor%3E%20%3Fa1%20.%20%3Fp1%20%3ChasFamilyName%3E%20%3Ffn%20.%20%3Fp1%20%3ChasGivenName%3E%20%3Fgn%20.%20%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fgn%20%3Ffn%20where%20%7B%20%3Fp1%20%3ChasAcademicAdvisor%3E%20%3Fa1%20.%20%3Fp1%20%3ChasFamilyName%3E%20%3Ffn%20.%20%3Fp1%20%3ChasGivenName%3E%20%3Fgn%20.%20%3Fp1%20%3CisMarriedTo%3E%20%3Fp2%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fn1%20%3Fn2%20where%20%7B%20%3Fp1%20%3CbornIn%3E%20%3Fc1%20.%20%3Fp2%20%3CbornIn%3E%20%3Fc1%20.%20%3Fp1%20%3ChasGivenName%3E%20%3Fn1%20.%20%3Fp2%20%3ChasGivenName%3E%20%3Fn2%20.%20%3Fp1%20%3CisMarriedTo%3E%20%3Fp2%20.%20%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fx%20where%20%7B%20%3Fx%20%3ClivesIn%3E%20%3CAthens%3E%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fy%20%3Fu%20where%20%7B%20%3CAlbert_Einstein%3E%20%3CgraduatedFrom%3E%20%3Fu%20.%20%3Fy%20%3CgraduatedFrom%3E%20%3Fu%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fn1%20where%20%7B%20%3Fa1%20%3CactedIn%3E%20%3Fm1%20.%20%3Fa1%20%3Cdirected%3E%20%3Fm2%20.%20%3Fa1%20%3ChasGivenName%3E%20%3Fn1%20.%20%3Fa1%20%3ClivesIn%3E%20%3Fc1%20.%20%3Fc1%20%3ClocatedIn%3E%20%3Fs%20.%20%3Fm1%20a%20%3Cwikicategory_2000_films%3E%20.%20%3Fm2%20a%20%3Cwikicategory_2000_films%3E%20.%20%3Fa1%20a%20%3Cwordnet_actor_109765278%3E%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20distinct%20%3Fn1%20%3Fn2%20where%20%7B%20%3Fa1%20%3CactedIn%3E%20%3Fm1%20.%20%3Fa2%20%3CactedIn%3E%20%3Fm1.%20%3Fa1%20%3ChasGivenName%3E%20%3Fn1%20.%20%3Fa2%20%3ChasGivenName%3E%20%3Fn2%20.%20%3Fa1%20%3ClivesIn%3E%20%3Fc1%20.%20%3Fa2%20%3ClivesIn%3E%20%3Fc1%20.%20filter%20%28%3Fa1%20%21%3D%20%3Fa2%29%20.%20%7D"
"BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20%3Fn1%20%3Fn2%20where%20%7B%20%3Fp1%20%3CbornIn%3E%20%3Fc1%20.%20%3Fp2%20%3CbornIn%3E%20%3Fc1%20.%20%3Fp1%20%3ChasGivenName%3E%20%3Fn1%20.%20%3Fp2%20%3ChasGivenName%3E%20%3Fn2%20.%20%3Fp1%20%3CisMarriedTo%3E%20%3Fp2%20.%20%7D"
# more than 10seconds "BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20PREFIX%20rdfs%3A%20%3Chttp%3A%2F%2Fwww.w3.org%2F2000%2F01%2Frdf-schema%23%3E%20select%20distinct%20%3Fn1%20where%20%7B%20%3Fp1%20%3ChasGivenName%3E%20%3Fn1%20.%20%3Fc2%20a%20%3Cwordnet_site_108651247%3E%20.%20%3Fc1%20a%20%3Cwordnet_village_108672738%3E%20.%20%3Fc1%20rdfs%3Alabel%20%27London%27%20.%20%3Fc2%20rdfs%3Alabel%20%27Paris%27%20.%20%3Fp1%20%3Fx1%20%3Fc1%20.%20%3Fp1%20%3Fy1%20%3Fc2%20.%20%7D"
# very slow "BASE%20%3Chttp%3A%2F%2Fyago-knowledge.org%2Fresource%2F%3E%20select%20distinct%20%3Fn1%20%3Fn2%20where%20%7B%20%3Fp1%20%3ChasFamilyName%3E%20%3Fn1%20.%20%3Fp2%20%3ChasFamilyName%3E%20%3Fn2%20.%20%3Fp1%20a%20%3Cwordnet_scientist_110560637%3E%20.%20%3Fp2%20a%20%3Cwordnet_scientist_110560637%3E%20.%20%3Fp1%20%3Fx1%20%3Fc1%20.%20%3Fp2%20%3Fy1%20%3Fc1%20.%20filter%20%28%3Fp1%20%21%3D%20%3Fp2%29%20.%20%7D"
)
mkdir -p src/luposdate3000_spa_client/dist
./launcher.main.kts --help
pkill java
if true
then
./launcher.main.kts --run &
sleep 5
benchmarkfile="benchmarks-luposdate3000-single-instance.csv"
echo $benchmarkfile
START=$(date +%s.%N)
curl "localhost:80/import/turtle?file=%2Fmnt%2Fluposdate-testdata%2Fyago1%2Fyago-1.0.0-turtle.ttl"
END=$(date +%s.%N)
DIFF=$(echo "$END - $START"| bc)
echo "import-time $DIFF" >> $benchmarkfile
for query in "${queries1[@]}"
do
echo $query
curl "localhost:80/sparql/benchmark?query=$query" >> $benchmarkfile
done
fi
pkill java
if true
then
./launcher.main.kts --run --partitionMode=Thread &
sleep 5
benchmarkfile="benchmarks-luposdate3000-single-instance-threads.csv"
echo $benchmarkfile
START=$(date +%s.%N)
curl "localhost:80/import/turtle?file=%2Fmnt%2Fluposdate-testdata%2Fyago1%2Fyago-1.0.0-turtle.ttl"
END=$(date +%s.%N)
DIFF=$(echo "$END - $START"| bc)
echo "import-time $DIFF" >> $benchmarkfile
for query in "${queries2[@]}"
do
echo $query
curl "localhost:80/sparql/benchmark?query=$query" >> $benchmarkfile
done
fi
pkill java
if true
then
for schema in "byId4"
do
for processCount in 4
do
./launcher.main.kts --run --partitionMode=Process --processCount=$processCount &
sleep 5
benchmarkfile="benchmarks-luposdate3000-multi-instance-$processCount-$schema.csv"
echo $benchmarkfile
curl "localhost:80/import/partition/scheme?file=${schema}.partition"
START=$(date +%s.%N)
curl "localhost:80/import/turtle?file=%2Fmnt%2Fluposdate-testdata%2Fyago1%2Fyago-1.0.0-turtle.ttl"
END=$(date +%s.%N)
DIFF=$(echo "$END - $START"| bc)
echo "import-time $DIFF" >> $benchmarkfile
for query in "${queries3[@]}"
do
echo $query
curl "localhost:80/sparql/benchmark?query=$query" >> $benchmarkfile
done
pkill java
done
done
fi
pkill java
