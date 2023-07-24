touch config.csv2
rm config.csv2

# files with ubungen_d contain initial data
# files with ubungen_s contain solution data
# files with ubungen_q contain initial queries
# files with ubungen_t contain target queries


function generateConfig() {
moduleName=$1
queryNumber=$2
queryIn=$3
dataIn=$4
queryTarget=$5
dataOut=$6

cat <<EOF > "config${moduleName}_${queryNumber}.json"
{
    "colorsByType": {
        "AOPAddition": "rgb(123,174,236)",
        "AOPAggregationAVG": "rgb(10,255,74)",
        "AOPAggregationCOUNT": "rgb(202,13,110)",
        "AOPAggregationMAX": "rgb(72,168,41)",
        "AOPAggregationMIN": "rgb(116,145,84)",
        "AOPAggregationSAMPLE": "rgb(28,137,207)",
        "AOPAggregationSUM": "rgb(144,156,38)",
        "AOPAnd": "rgb(130,43,248)",
        "AOPBinaryOperationFixedName": "rgb(34,81,21)",
        "AOPBuildInCallABS": "rgb(136,166,89)",
        "AOPBuildInCallBNODE0": "rgb(156,41,98)",
        "AOPBuildInCallBNODE1": "rgb(67,90,158)",
        "AOPBuildInCallBOUND": "rgb(167,203,198)",
        "AOPBuildInCallCEIL": "rgb(123,94,70)",
        "AOPBuildInCallCOALESCE": "rgb(175,67,45)",
        "AOPBuildInCallCONCAT": "rgb(3,11,74)",
        "AOPBuildInCallCONTAINS": "rgb(31,170,252)",
        "AOPBuildInCallDATATYPE": "rgb(104,157,65)",
        "AOPBuildInCallDAY": "rgb(82,18,161)",
        "AOPBuildInCallExists": "rgb(204,243,85)",
        "AOPBuildInCallFLOOR": "rgb(181,116,49)",
        "AOPBuildInCallHOURS": "rgb(88,142,76)",
        "AOPBuildInCallIF": "rgb(103,69,156)",
        "AOPBuildInCallIRI": "rgb(67,222,225)",
        "AOPBuildInCallIsIri": "rgb(208,93,207)",
        "AOPBuildInCallIsLITERAL": "rgb(225,151,146)",
        "AOPBuildInCallIsNUMERIC": "rgb(1,78,101)",
        "AOPBuildInCallLANG": "rgb(64,137,251)",
        "AOPBuildInCallLANGMATCHES": "rgb(26,168,85)",
        "AOPBuildInCallLCASE": "rgb(175,159,143)",
        "AOPBuildInCallMD5": "rgb(174,234,85)",
        "AOPBuildInCallMINUTES": "rgb(235,212,132)",
        "AOPBuildInCallMONTH": "rgb(46,1,88)",
        "AOPBuildInCallNotExists": "rgb(170,65,79)",
        "AOPBuildInCallROUND": "rgb(89,179,33)",
        "AOPBuildInCallSECONDS": "rgb(79,187,73)",
        "AOPBuildInCallSHA1": "rgb(246,72,238)",
        "AOPBuildInCallSHA256": "rgb(40,133,119)",
        "AOPBuildInCallSTR": "rgb(27,198,224)",
        "AOPBuildInCallSTRAFTER": "rgb(14,74,225)",
        "AOPBuildInCallSTRBEFORE": "rgb(149,73,186)",
        "AOPBuildInCallSTRDT": "rgb(116,146,186)",
        "AOPBuildInCallSTRENDS": "rgb(109,176,201)",
        "AOPBuildInCallSTRLANG": "rgb(159,111,123)",
        "AOPBuildInCallSTRLEN": "rgb(81,139,240)",
        "AOPBuildInCallSTRSTARTS": "rgb(162,143,164)",
        "AOPBuildInCallSTRUUID": "rgb(206,56,203)",
        "AOPBuildInCallTIMEZONE": "rgb(134,46,67)",
        "AOPBuildInCallTZ": "rgb(230,233,193)",
        "AOPBuildInCallUCASE": "rgb(148,112,177)",
        "AOPBuildInCallURI": "rgb(17,129,144)",
        "AOPBuildInCallUUID": "rgb(38,82,163)",
        "AOPBuildInCallYEAR": "rgb(182,252,248)",
        "AOPConstant": "rgb(51,219,19)",
        "AOPDivision": "rgb(82,160,120)",
        "AOPEQ": "rgb(99,137,180)",
        "AOPFunctionCallDouble": "rgb(105,135,69)",
        "AOPFunctionCallFloat": "rgb(56,166,112)",
        "AOPFunctionCallString": "rgb(190,179,245)",
        "AOPGEQ": "rgb(131,48,195)",
        "AOPGT": "rgb(93,127,246)",
        "AOPIn": "rgb(173,27,195)",
        "AOPLEQ": "rgb(174,74,83)",
        "AOPLT": "rgb(164,17,59)",
        "AOPMultiplication": "rgb(245,225,54)",
        "AOPNEQ": "rgb(232,69,23)",
        "AOPNot": "rgb(98,135,126)",
        "AOPNotIn": "rgb(152,220,92)",
        "AOPOr": "rgb(109,250,38)",
        "AOPSet": "rgb(69,5,52)",
        "AOPSubtraction": "rgb(20,146,41)",
        "AOPValue": "rgb(214,70,31)",
        "AOPVariable": "rgb(179,229,75)",
        "LOPBind": "rgb(215,58,18)",
        "LOPDistinct": "rgb(223,178,114)",
        "LOPFilter": "rgb(86,246,255)",
        "LOPGraphOperation": "rgb(188,114,217)",
        "LOPGroup": "rgb(22,49,221)",
        "LOPJoin": "rgb(213,116,160)",
        "LOPLimit": "rgb(27,127,131)",
        "LOPMakeBooleanResult": "rgb(146,172,18)",
        "LOPMinus": "rgb(250,69,204)",
        "LOPModify": "rgb(124,123,134)",
        "LOPModifyData": "rgb(134,153,170)",
        "LOPNOOP": "rgb(217,41,43)",
        "LOPOffset": "rgb(139,102,230)",
        "LOPOptional": "rgb(206,90,189)",
        "LOPPrefix": "rgb(169,94,230)",
        "LOPProjection": "rgb(63,164,231)",
        "LOPReduced": "rgb(56,240,244)",
        "LOPServiceIRI": "rgb(199,11,189)",
        "LOPServiceVAR": "rgb(37,213,142)",
        "LOPSort": "rgb(65,67,232)",
        "LOPSortAny": "rgb(240,170,104)",
        "LOPSubGroup": "rgb(137,142,140)",
        "LOPTriple": "rgb(253,15,25)",
        "LOPUnion": "rgb(70,165,17)",
        "LOPValues": "rgb(54,31,121)",
        "OPBaseCompound": "rgb(252,83,38)",
        "OPEmptyRow": "rgb(223,216,253)",
        "OPNothing": "rgb(78,0,75)",
        "POPBind": "rgb(36,209,68)",
        "POPChangePartitionOrderedByIntId": "rgb(61,210,171)",
        "POPDebug": "rgb(150,54,125)",
        "POPDistributedReceiveMulti": "rgb(28,179,15)",
        "POPDistributedReceiveMultiCount": "rgb(65,100,218)",
        "POPDistributedReceiveMultiOrdered": "rgb(118,174,222)",
        "POPDistributedReceiveSingle": "rgb(39,206,209)",
        "POPDistributedReceiveSingleCount": "rgb(209,89,40)",
        "POPDistributedSendMulti": "rgb(49,111,114)",
        "POPDistributedSendSingle": "rgb(214,83,39)",
        "POPDistributedSendSingleCount": "rgb(182,160,7)",
        "POPEmptyRow": "rgb(121,56,75)",
        "POPFilter": "rgb(26,120,255)",
        "POPGraphOperation": "rgb(75,224,5)",
        "POPGroup": "rgb(207,103,43)",
        "POPGroup_Row": "rgb(20,181,178)",
        "POPJoinCartesianProduct": "rgb(199,179,95)",
        "POPJoinHashMap": "rgb(148,104,24)",
        "POPJoinMerge": "rgb(101,211,84)",
        "POPJoinMerge_Iterator": "rgb(232,223,144)",
        "POPJoinMergeOptional": "rgb(219,197,201)",
        "POPJoinMergeSingleColumn": "rgb(178,241,202)",
        "POPLimit": "rgb(66,0,25)",
        "POPMakeBooleanResult": "rgb(37,10,247)",
        "POPMergePartition": "rgb(179,133,29)",
        "POPMergePartitionCount": "rgb(66,246,42)",
        "POPMergePartitionOrderedByIntId": "rgb(118,161,180)",
        "POPMinus": "rgb(32,13,59)",
        "POPModify": "rgb(181,145,162)",
        "POPModifyData": "rgb(224,68,81)",
        "POPOffset": "rgb(229,46,11)",
        "POPProjection": "rgb(215,190,187)",
        "POPReduced": "rgb(23,255,61)",
        "POPSort": "rgb(168,197,216)",
        "POPSplitPartition": "rgb(207,241,212)",
        "POPSplitPartitionFromStore": "rgb(111,54,159)",
        "POPSplitPartitionFromStoreCount": "rgb(161,144,206)",
        "POPSplitPartitionPassThrough": "rgb(251,49,132)",
        "POPTripleStoreIterator": "rgb(221,124,30)",
        "POPUnion": "rgb(154,238,163)",
        "POPValues": "rgb(255,50,134)",
        "POPValues2": "rgb(26,43,230)",
        "POPValuesImportBase": "rgb(88,214,136)",
        "POPValuesImportXML": "rgb(89,36,175)",
        "POPVisualisation": "rgb(17,76,23)"
    },
    "animationSpeed": 80,
    "evalGraphSparql": true,
    "evalGraphRif": true,
    "sonification": {
        "Pitch": {
            "mode": "None"
        },
        "Instrument": {
            "mode": "None"
        },
        "Loudness": {
            "mode": "None"
        },
        "Spatialization": {
            "mode": "None"
        },
        "Duration": {
            "mode": "None"
        },
        "Melody": {
            "mode": "No"
        },
        "Chord": {
            "mode": "None"
        },
        "Octave": {
            "mode": "None"
        }
    },
    "endpoints": [{
        "name": "Browser Luposdate3000",
        "nonstandard": false,
        "evaluators": ["Luposdate3000"],
        "selectedEvaluator": 0
    }, {
        "name": "localhost Luposdate3000",
        "nonstandard": false,
        "url": "http://benjamin0:80/",
        "without": "",
        "evaluators": ["Luposdate3000"],
        "selectedEvaluator": 0
    }, {
        "url": "https://www.ifis.uni-luebeck.de/sparql-endpoint/",
        "name": "IFIS Luposdate",
        "nonstandard": true,
        "sparql": ["nonstandard/sparql", "POST"],
        "rif": ["nonstandard/rif", "POST"],
        "evaluators": ["MemoryIndex", "RDF3X", "Stream", "Jena", "Sesame"],
        "selectedEvaluator": 0
    }, {
        "url": "http://localhost:8080/",
        "name": "localhost Luposdate",
        "nonstandard": true,
        "without": "sparql",
        "sparql": ["nonstandard/sparql", "POST"],
        "rif": ["nonstandard/rif", "POST"],
        "evaluators": ["MemoryIndex", "RDF3X", "Stream", "Jena", "Sesame"],
        "selectedEvaluator": 0
    }],
    "selectedEndpoint": 0,
"defaultData": {
    "sparql": ["resources/$queryTarget"],
    "rdf": ["resources/$dataIn"],
    "rif": []
  },
    "sendRDF": true,
    "hide": {
      "sendRDF": true,
      "inference": true,
      "withGraph": true,
      "tabs": ["rif", "graph"]
    },
    "readOnlyTabs": ["rdf"],
    "queryParameters": {
        "inference": "NONE",
        "inferenceGeneration": "GENERATEDOPT"
    }
}
EOF

echo $(wc -l $dataIn | sed "s/ .*//g"),$queryTarget,$dataIn,$dataOut >> config.csv2
}




#https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/sw/sparql
#wget -O "ubungen_d1.n3" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/Luebeck.n3
#wget -O "ubungen_d2.n3" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/University_of_Luebeck.n3
#wget -O "ubungen_d3.n3" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/BL.n3
#wget -O "ubungen_t01.sparql" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/s1a.sparql
#wget -O "ubungen_t02.sparql" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/s1b.sparql
#wget -O "ubungen_t03.sparql" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/s1c.sparql
#wget -O "ubungen_t04.sparql" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/s1d.sparql
#wget -O "ubungen_t05.sparql" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/s1e.sparql
#wget -O "ubungen_t06.sparql" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/s1f.sparql
#wget -O "ubungen_q01.sparql" https://www.ifis.uni-luebeck.de/~groppe/cloud-and-web-technologies/resources/sparql/empty.sparql

generateConfig "cloud-and-web-technologies" "1" "ubungen_q01.sparql" "ubungen_d1.n3" "ubungen_t01.sparql" ""
generateConfig "cloud-and-web-technologies" "2" "ubungen_q01.sparql" "ubungen_d1.n3" "ubungen_t02.sparql" ""
generateConfig "cloud-and-web-technologies" "3" "ubungen_q01.sparql" "ubungen_d1.n3" "ubungen_t03.sparql" ""
generateConfig "cloud-and-web-technologies" "4" "ubungen_q01.sparql" "ubungen_d1.n3" "ubungen_t04.sparql" ""
generateConfig "cloud-and-web-technologies" "5" "ubungen_q01.sparql" "ubungen_d2.n3" "ubungen_t05.sparql" ""
generateConfig "cloud-and-web-technologies" "6" "ubungen_q01.sparql" "ubungen_d3.n3" "ubungen_t06.sparql" ""

#https://www.ifis.uni-luebeck.de/~groppe/sw/sparql10
#wget -O "ubungen_d4.n3" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/lubm_demo.n3
#wget -O "ubungen_t07.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query1.rq
#wget -O "ubungen_t08.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query2.rq
#wget -O "ubungen_t09.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query3.rq
#wget -O "ubungen_t10.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query4.rq
#wget -O "ubungen_t11.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query5.rq
#wget -O "ubungen_t12.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query6.rq
#wget -O "ubungen_t13.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query7.rq
#wget -O "ubungen_t14.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query8.rq
#wget -O "ubungen_t15.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query9.rq
#wget -O "ubungen_t16.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/query10.rq
#wget -O "ubungen_q02.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql10/lubm_test.sparql

generateConfig "sw-sparql1.0" "1" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t07.rq" ""
generateConfig "sw-sparql1.0" "2" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t08.rq" ""
generateConfig "sw-sparql1.0" "3" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t09.rq" ""
generateConfig "sw-sparql1.0" "4" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t10.rq" ""
generateConfig "sw-sparql1.0" "5" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t11.rq" ""
generateConfig "sw-sparql1.0" "6" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t12.rq" ""
generateConfig "sw-sparql1.0" "7" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t13.rq" ""
generateConfig "sw-sparql1.0" "8" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t14.rq" ""
generateConfig "sw-sparql1.0" "9" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t15.rq" ""
generateConfig "sw-sparql1.0" "10" "ubungen_q02.sparql" "ubungen_d4.n3" "ubungen_t16.rq" ""

#https://www.ifis.uni-luebeck.de/~groppe/sw/sparql11
#wget -O "ubungen_d5.n3" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/sp2b.n3
#wget -O "ubungen_t17.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query1.rq
#wget -O "ubungen_t18.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query2.rq
#wget -O "ubungen_t19.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query3.rq
#wget -O "ubungen_t20.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query4.rq
#wget -O "ubungen_t21.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query5.rq
#wget -O "ubungen_t22.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/query6.rq
#wget -O "ubungen_q03.sparql" https://www.ifis.uni-luebeck.de/~groppe/sw/resources/sparql11/startquery.rq

generateConfig "sw-sparql1.1" "1" "ubungen_q03.sparql" "ubungen_d5.n3" "ubungen_t17.rq" ""
generateConfig "sw-sparql1.1" "2" "ubungen_q03.sparql" "ubungen_d5.n3" "ubungen_t18.rq" ""
generateConfig "sw-sparql1.1" "3" "ubungen_q03.sparql" "ubungen_d5.n3" "ubungen_t19.rq" ""
generateConfig "sw-sparql1.1" "4" "ubungen_q03.sparql" "ubungen_d5.n3" "ubungen_t20.rq" ""
generateConfig "sw-sparql1.1" "5" "ubungen_q03.sparql" "ubungen_d5.n3" "ubungen_t21.rq" ""
generateConfig "sw-sparql1.1" "6" "ubungen_q03.sparql" "ubungen_d5.n3" "ubungen_t22.rq" ""


cat config.csv2 | sort -n | uniq > config.csv
rm config.csv2