lupos.ProguardKeepAnnotation
lupos.ProguardTestAnnotation
lupos.buffer_manager.BufferManager:
    57:57:public int getNumberOfAllocatedPages()
    61:196:public int getNumberOfReferencedPages()
lupos.buffer_manager.MyIntArray:
    public static synthetic void getClosed$luposdate3000_buffer_manager_inmemory$annotations()
    101:194:public final void setSize(int)
    128:198:public final void delete()
lupos.dictionary.DictionaryFactory:
    66:75:public final lupos.shared.dictionary.IDictionary createDictionary(int,boolean,lupos.shared.IBufferManager,int,boolean,lupos.shared.Luposdate3000Instance)
lupos.dictionary.DictionaryInMemory:
    45:45:public boolean isInmemoryOnly()
    51:196:public void delete()
    70:338:public void forEachValue(lupos.shared.dynamicArray.ByteArrayWrapper,kotlin.jvm.functions.Function1)
lupos.dictionary.DictionaryKV:
    67:339:public void delete()
    74:74:public boolean isInmemoryOnly()
    76:470:public void forEachValue(lupos.shared.dynamicArray.ByteArrayWrapper,kotlin.jvm.functions.Function1)
    35:35:public static final synthetic int access$getOffsetBNodeCounter$cp()
    35:35:public static final synthetic int access$getOffsetkvPage$cp()
    35:35:public static final synthetic int access$getOffsetvkPage$cp()
    35:35:public static final synthetic int access$getOffsetuuidCounter$cp()
lupos.dictionary.DictionaryKV$Companion:
    103:103:public final int getOffsetBNodeCounter$luposdate3000_dictionary()
    104:104:public final int getOffsetkvPage$luposdate3000_dictionary()
    105:105:public final int getOffsetvkPage$luposdate3000_dictionary()
    106:106:public final int getOffsetuuidCounter$luposdate3000_dictionary()
lupos.endpoint.LuposdateEndpoint:
    138:138:private final java.lang.String importIntermediateFile(lupos.shared.Luposdate3000Instance,java.lang.String)
    246:246:public final lupos.shared.operator.IOPBase evaluateSparqlToOperatorgraphA(lupos.shared.Luposdate3000Instance,java.lang.String)
    323:323:public final java.lang.String evaluateSparqlToResultB(lupos.shared.Luposdate3000Instance,java.lang.String)
    328:331:public final java.lang.String evaluateSparqlToResultC(lupos.shared.Luposdate3000Instance,java.lang.String,boolean)
    336:337:public final void evaluateSparqlToResultA(lupos.shared.Luposdate3000Instance,java.lang.String,lupos.shared.IMyOutputStream)
    341:343:public final void evaluateSparqlToResultD(lupos.shared.Luposdate3000Instance,java.lang.String,lupos.shared.IMyOutputStream,boolean)
    347:347:public final java.lang.String evaluateOperatorgraphxmlToResultA(lupos.shared.Luposdate3000Instance,java.lang.String)
    352:792:public final java.lang.String evaluateOperatorgraphxmlToResultB(lupos.shared.Luposdate3000Instance,java.lang.String,boolean)
lupos.endpoint.QueryResultToStreamKt
lupos.endpoint_launcher.RemoteDictionaryClient:
    30:30:public void forEachValue(lupos.shared.dynamicArray.ByteArrayWrapper,kotlin.jvm.functions.Function1)
    31:31:public boolean isInmemoryOnly()
    33:33:public void delete()
lupos.endpoint_launcher.RemoteDictionaryServer:
    32:32:public void forEachValue(lupos.shared.dynamicArray.ByteArrayWrapper,kotlin.jvm.functions.Function1)
    33:33:public boolean isInmemoryOnly()
    35:35:public void delete()
lupos.endpoint_launcher.RestEndpoint:
    61:63:private final lupos.endpoint_launcher.RemoteDictionaryServer registerDictionary(lupos.shared.Luposdate3000Instance,java.lang.String)
    68:70:private final lupos.endpoint_launcher.RemoteDictionaryServer registerDictionary(lupos.shared.Luposdate3000Instance,java.lang.String,lupos.shared.dictionary.IDictionary)
    75:76:private final void removeDictionary(java.lang.String)
lupos.jena_wrapper.JenaWrapper:
    21:21:public final void dropAll()
    31:31:public final void loadFromFile(java.lang.String,java.lang.String)
lupos.kv.KeyValueStore:
    91:299:public final void delete()
    122:320:private final void readData(lupos.shared.dynamicArray.ByteArrayWrapper,int,int)
    153:418:private final void writeData(lupos.shared.dynamicArray.ByteArrayWrapper,kotlin.jvm.functions.Function2)
lupos.operator.arithmetik.AOPBase:
    83:88:public int getPartitionCount(java.lang.String)
lupos.operator.arithmetik.singleinput.AOPAggregationCOUNT$ColumnIteratorAggregateCOUNT:
    44:44:public final java.util.Set getMyList()
lupos.operator.arithmetik.singleinput.AOPAggregationSAMPLE$ColumnIteratorAggregateSAMPLE:
    private boolean isError
lupos.operator.base.ConfigKt
lupos.operator.base.OPBase:
    95:95:public boolean getSortPrioritiesInitialized()
    97:98:public void setSortPriorities(java.util.List)
lupos.operator.base.OPBaseCompound:
    29:86:public int getPartitionCount(java.lang.String)
lupos.operator.base.Query:
    33:33:public Query(lupos.shared.dictionary.IDictionary,lupos.shared.Luposdate3000Instance)
    89:89:public java.util.Map getOperatorgraphPartsToHostMap()
    90:90:public java.util.Map getOperatorgraphParts()
    91:91:public java.util.Map getDependenciesMapTopDown()
    102:102:public lupos.shared.operator.IOPBase getRoot()
    169:174:public final void setWorkingDirectory(java.lang.String)
lupos.operator.base.iterator.ColumnIteratorMultiIterator:
    32:38:public final void _close$luposdate3000_operator_base()
lupos.operator.base.iterator.ColumnIteratorMultiValue3:
    29:31:public final void reset(int)
lupos.operator.base.iterator.ColumnIteratorReduced:
    32:36:public final void _close$luposdate3000_operator_base()
lupos.operator.base.iterator.ColumnIteratorRepeatIterator:
    39:43:public final void _close$luposdate3000_operator_base()
lupos.operator.base.iterator.RowIteratorMerge:
    302:326:private final void compare(kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0)
lupos.operator.base.noinput.OPEmptyRow:
    28:39:public int getPartitionCount(java.lang.String)
lupos.operator.base.singleinput.LOPNOOP:
    31:39:public int getPartitionCount(java.lang.String)
lupos.operator.factory.XMLElementToOPBase:
    176:176:public final java.util.Map getOperatorMap()
lupos.operator.factory.XMLElementToOPBaseKt
lupos.operator.logical.LOPBase:
    35:38:public int getPartitionCount(java.lang.String)
lupos.operator.physical.ConfigKt:
    static void <clinit>()
lupos.operator.physical.multiinput.POPJoinCartesianProduct:
    43:276:public int getPartitionCount(java.lang.String)
lupos.operator.physical.multiinput.POPJoinCartesianProduct$evaluate$iterator$1:
    155:313:public final void __close()
lupos.operator.physical.multiinput.POPJoinCartesianProduct$evaluate$iterator$2:
    222:313:public final void __close()
lupos.operator.physical.multiinput.POPJoinHashMap:
    39:344:public int getPartitionCount(java.lang.String)
lupos.operator.physical.multiinput.POPJoinHashMap$evaluate$iterator$1:
    178:394:public final void __close()
lupos.operator.physical.multiinput.POPJoinMerge:
    35:142:public int getPartitionCount(java.lang.String)
lupos.operator.physical.multiinput.POPJoinMergeOptional:
    39:292:public int getPartitionCount(java.lang.String)
    229:329:public final int sameElements$luposdate3000_operator_physical(long[],long[],java.util.List,java.util.List,java.util.List[])
    261:333:public final boolean findNextKey$luposdate3000_operator_physical(long[][],java.util.List[],java.util.List[])
lupos.operator.physical.multiinput.POPJoinMergeSingleColumn:
    34:97:public int getPartitionCount(java.lang.String)
lupos.operator.physical.multiinput.POPJoinMergeSingleColumn_Iterator:
    122:127:public final void _close$luposdate3000_operator_physical()
lupos.operator.physical.multiinput.POPJoinMerge_Bundle:
    39:49:private final void _hasNext2Close()
lupos.operator.physical.multiinput.POPJoinMerge_Iterator:
    77:337:private final void __close()
lupos.operator.physical.multiinput.POPJoinWithStore:
    48:48:public int getPartitionCount(java.lang.String)
lupos.operator.physical.multiinput.POPJoinWithStore$evaluate$column$1:
    174:301:public final void __close()
lupos.operator.physical.multiinput.POPJoinWithStoreExists:
    37:37:public int getPartitionCount(java.lang.String)
lupos.operator.physical.multiinput.POPMinus:
    31:76:public int getPartitionCount(java.lang.String)
lupos.operator.physical.multiinput.POPUnion:
    32:91:public int getPartitionCount(java.lang.String)
lupos.operator.physical.noinput.POPEmptyRow:
    28:28:public int getPartitionCount(java.lang.String)
lupos.operator.physical.noinput.POPGraphOperation:
    54:54:public int getPartitionCount(java.lang.String)
lupos.operator.physical.noinput.POPModifyData:
    42:42:public int getPartitionCount(java.lang.String)
lupos.operator.physical.noinput.POPValues:
    38:38:public int getPartitionCount(java.lang.String)
lupos.operator.physical.noinput.POPValues2:
    30:30:public int getPartitionCount(java.lang.String)
lupos.operator.physical.noinput.POPValuesImportBase
lupos.operator.physical.noinput.POPValuesImportXML
lupos.operator.physical.partition.POPChangePartitionOrderedByIntId:
    52:55:public int getPartitionCount(java.lang.String)
lupos.operator.physical.partition.POPDistributedReceiveMulti:
    50:53:public int getPartitionCount(java.lang.String)
lupos.operator.physical.partition.POPDistributedReceiveMultiCount:
    43:46:public int getPartitionCount(java.lang.String)
lupos.operator.physical.partition.POPDistributedReceiveMultiOrdered:
    50:53:public int getPartitionCount(java.lang.String)
lupos.operator.physical.partition.POPDistributedReceiveSingle:
    47:50:public int getPartitionCount(java.lang.String)
lupos.operator.physical.partition.POPDistributedReceiveSingleCount:
    40:43:public int getPartitionCount(java.lang.String)
lupos.operator.physical.partition.POPDistributedSendMulti:
    47:50:public int getPartitionCount(java.lang.String)
lupos.operator.physical.partition.POPDistributedSendSingle:
    47:50:public int getPartitionCount(java.lang.String)
lupos.operator.physical.partition.POPDistributedSendSingleCount:
    42:45:public int getPartitionCount(java.lang.String)
lupos.operator.physical.partition.POPMergePartition:
    45:48:public int getPartitionCount(java.lang.String)
lupos.operator.physical.partition.POPMergePartitionCount:
    37:40:public int getPartitionCount(java.lang.String)
lupos.operator.physical.partition.POPMergePartitionOrderedByIntId:
    46:49:public int getPartitionCount(java.lang.String)
lupos.operator.physical.partition.POPSplitPartition:
    46:49:public int getPartitionCount(java.lang.String)
lupos.operator.physical.partition.POPSplitPartitionFromStore:
    40:43:public int getPartitionCount(java.lang.String)
lupos.operator.physical.partition.POPSplitPartitionFromStoreCount:
    35:38:public int getPartitionCount(java.lang.String)
lupos.operator.physical.partition.POPSplitPartitionPassThrough:
    40:43:public int getPartitionCount(java.lang.String)
lupos.operator.physical.singleinput.POPBind:
    42:45:public int getPartitionCount(java.lang.String)
lupos.operator.physical.singleinput.POPDebug:
    38:38:public int getPartitionCount(java.lang.String)
lupos.operator.physical.singleinput.POPFilter:
    36:36:public int getPartitionCount(java.lang.String)
lupos.operator.physical.singleinput.POPFilter$evaluate$1:
    71:228:public final void __close()
lupos.operator.physical.singleinput.POPGroup:
    73:574:public int getPartitionCount(java.lang.String)
lupos.operator.physical.singleinput.POPGroup$evaluate$iterator$1:
    329:588:public final void __close()
lupos.operator.physical.singleinput.POPMakeBooleanResult:
    34:74:public int getPartitionCount(java.lang.String)
lupos.operator.physical.singleinput.POPModify:
    62:229:public int getPartitionCount(java.lang.String)
lupos.operator.physical.singleinput.POPProjection:
    32:32:public int getPartitionCount(java.lang.String)
lupos.operator.physical.singleinput.POPSort:
    40:162:public int getPartitionCount(java.lang.String)
lupos.operator.physical.singleinput.POPVisualisation:
    44:44:public int getPartitionCount(java.lang.String)
lupos.operator.physical.singleinput.modifiers.POPLimit:
    35:98:public int getPartitionCount(java.lang.String)
lupos.operator.physical.singleinput.modifiers.POPLimit$evaluate$tmp$1:
    79:83:public final void _close()
lupos.operator.physical.singleinput.modifiers.POPOffset:
    35:79:public int getPartitionCount(java.lang.String)
lupos.operator.physical.singleinput.modifiers.POPReduced:
    30:30:public int getPartitionCount(java.lang.String)
lupos.optimizer.ast.EGroupMemberExt
lupos.optimizer.ast.EGroupMemberKt
lupos.optimizer.logical.ConfigKt
lupos.optimizer.logical.EOptimizerIDExt
lupos.optimizer.logical.EOptimizerIDKt
lupos.optimizer.logical.OptimizerCompoundBase:
    34:152:private final void verifyPartitionOperators(lupos.shared.operator.IOPBase,java.util.Map,java.util.Map,lupos.shared.operator.IOPBase)
    30:30:public static final synthetic void access$verifyPartitionOperators(lupos.optimizer.logical.OptimizerCompoundBase,lupos.shared.operator.IOPBase,java.util.Map,java.util.Map,lupos.shared.operator.IOPBase)
lupos.parser.InputToIntermediate:
    46:60:private final java.lang.String helperCleanString(java.lang.String)
    65:74:private final long cmp(long[],long[])
    78:162:private final void mergesort2(int,kotlin.jvm.functions.Function2,kotlin.jvm.functions.Function2,kotlin.jvm.functions.Function2,kotlin.jvm.functions.Function2,int)
    191:203:private static final int process$lambda-14$cmp(java.lang.String,java.lang.String)
lupos.parser.LexerCharIterator:
    public static final int MAXSIZEPUTBACK
    105:105:public final boolean hasNext$luposdate3000_parser()
    109:115:public final void updateLineNumber$luposdate3000_parser(char)
    119:125:public final void updateLineNumberforPutBack$luposdate3000_parser(char)
    129:234:public final char nextChar$luposdate3000_parser()
    147:241:public final void putBack$luposdate3000_parser(char)
    158:248:public final void putBack$luposdate3000_parser(java.lang.String)
    176:266:public final boolean lookaheadAvailable$luposdate3000_parser(int)
    175:266:public static synthetic boolean lookaheadAvailable$luposdate3000_parser$default(lupos.parser.LexerCharIterator,int,int,java.lang.Object)
    194:211:public final char lookahead$luposdate3000_parser(int)
    193:211:public static synthetic char lookahead$luposdate3000_parser$default(lupos.parser.LexerCharIterator,int,int,java.lang.Object)
lupos.parser.nQuads.NQuads2ParserGeneratedKt:
    185:1210:public static final void parse_dot(lupos.parser.nQuads.ParserContext,kotlin.jvm.functions.Function0)
    204:207:public static final int parse_dot_helper_0(int)
    215:1212:public static final void parse_ws(lupos.parser.nQuads.ParserContext,kotlin.jvm.functions.Function0)
    236:1228:public static final void parse_ws_forced(lupos.parser.nQuads.ParserContext,kotlin.jvm.functions.Function0)
    265:278:public static final int parse_ws_forced_helper_0(int)
    287:1264:public static final void parse_subject(lupos.parser.nQuads.ParserContext,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0)
    358:367:public static final int parse_subject_helper_0(int)
    373:376:public static final int parse_subject_helper_1(int)
    382:385:public static final int parse_subject_helper_2(int)
    391:406:public static final int parse_subject_helper_3(int)
    414:1274:public static final void parse_predicate(lupos.parser.nQuads.ParserContext,kotlin.jvm.functions.Function0)
    452:455:public static final int parse_predicate_helper_0(int)
    461:464:public static final int parse_predicate_helper_1(int)
    474:1486:public static final void parse_object(lupos.parser.nQuads.ParserContext,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0)
    700:713:public static final int parse_object_helper_0(int)
    719:722:public static final int parse_object_helper_1(int)
    728:731:public static final int parse_object_helper_2(int)
    737:752:public static final int parse_object_helper_3(int)
    832:845:public static final int parse_object_helper_6(int)
    851:854:public static final int parse_object_helper_7(int)
    864:1492:public static final void parse_object_string(lupos.parser.nQuads.ParserContext,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0)
    958:961:public static final int parse_object_string_helper_1(int)
    995:1502:public static final void parse_object_typed(lupos.parser.nQuads.ParserContext,kotlin.jvm.functions.Function0)
    1033:1036:public static final int parse_object_typed_helper_0(int)
    1042:1045:public static final int parse_object_typed_helper_1(int)
    1055:1528:public static final void parse_graph(lupos.parser.nQuads.ParserContext,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0)
    1168:1171:public static final int parse_graph_helper_1(int)
    1177:1180:public static final int parse_graph_helper_2(int)
    1186:1201:public static final int parse_graph_helper_3(int)
lupos.parser.nQuads.ParserContext:
    public static final int EOF
    57:58:public final void clear$luposdate3000_parser()
    62:62:public final java.lang.String getValue$luposdate3000_parser()
lupos.parser.rdf.BlankNode
lupos.parser.rdf.Dictionary
lupos.parser.rdf.ID_Triple
lupos.parser.rdf.IRI
lupos.parser.rdf.LanguageTaggedLiteral
lupos.parser.rdf.Literal
lupos.parser.rdf.RDFResource
lupos.parser.rdf.RDFTerm
lupos.parser.rdf.SimpleLiteral
lupos.parser.rdf.TypedLiteral
lupos.parser.sparql1_1.ASTDecimal:
    400:400:public final double toDouble()
lupos.parser.sparql1_1.ASTDescribeQuery:
    251:251:private final boolean selectAll()
lupos.parser.sparql1_1.ASTDouble:
    392:392:public final double toDouble()
lupos.parser.sparql1_1.ASTNode:
    46:51:protected final java.lang.String propertyToString(java.lang.String,java.lang.String,java.lang.String,lupos.parser.sparql1_1.ASTNode[])
    56:79:public final java.util.List getChildrensValues$luposdate3000_parser(lupos.parser.sparql1_1.Visitor)
lupos.parser.sparql1_1.ASTSelectQuery:
    223:223:protected final java.lang.String innerNodeToString()
lupos.parser.sparql1_1.TokenIteratorSPARQLParser:
    449:4942:private final lupos.parser.Token PNAME_LN_after_colon(java.lang.String,int)
    2:5107:private final lupos.parser.Token numberAfterDot(java.lang.String,int)
    570:5207:private final lupos.parser.Token numberAfterExp(java.lang.String,boolean,java.lang.String,char,int)
    599:5462:private final lupos.parser.Token dealWithString(char,int)
    652:665:private final boolean PN_CHARS_BASE(char)
    668:5476:private final boolean PN_CHARS_U(char)
    671:671:private final boolean DIGIT(char)
    675:5492:private final boolean VARNAMESECONDCHARANDLATER(char)
    682:5518:private final boolean PN_CHARS(char)
    685:5539:private final boolean PN_CHARS_U_or_DIGIT(char)
    688:715:private final boolean PN_LOCAL_ESC(char)
    718:727:private final boolean HEX(char)
lupos.parser.turtle.ParserContext:
    public static final int EOF
    57:58:public final void clear$luposdate3000_parser()
    62:62:public final java.lang.String getValue$luposdate3000_parser()
lupos.parser.turtle.Turtle2ParserGeneratedKt:
    185:9050:public static final void parse_dot(lupos.parser.turtle.ParserContext,kotlin.jvm.functions.Function0)
    204:207:public static final int parse_dot_helper_0(int)
    215:9052:public static final void parse_ws(lupos.parser.turtle.ParserContext,kotlin.jvm.functions.Function0)
    236:9068:public static final void parse_ws_forced(lupos.parser.turtle.ParserContext,kotlin.jvm.functions.Function0)
    265:278:public static final int parse_ws_forced_helper_0(int)
    292:9334:public static final void parse_statement(lupos.parser.turtle.ParserContext,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0)
    800:803:public static final int parse_statement_helper_1(int)
    809:812:public static final int parse_statement_helper_2(int)
    818:821:public static final int parse_statement_helper_3(int)
    827:830:public static final int parse_statement_helper_4(int)
    836:839:public static final int parse_statement_helper_5(int)
    845:848:public static final int parse_statement_helper_6(int)
    854:857:public static final int parse_statement_helper_7(int)
    863:872:public static final int parse_statement_helper_8(int)
    878:881:public static final int parse_statement_helper_9(int)
    887:890:public static final int parse_statement_helper_10(int)
    896:899:public static final int parse_statement_helper_11(int)
    905:908:public static final int parse_statement_helper_12(int)
    914:917:public static final int parse_statement_helper_13(int)
    923:926:public static final int parse_statement_helper_14(int)
    932:935:public static final int parse_statement_helper_15(int)
    983:992:public static final int parse_statement_helper_17(int)
    998:1011:public static final int parse_statement_helper_18(int)
    1017:1020:public static final int parse_statement_helper_19(int)
    1104:1107:public static final int parse_statement_helper_21(int)
    1185:9522:public static final void parse_base(lupos.parser.turtle.ParserContext,kotlin.jvm.functions.Function0)
    1350:1353:public static final int parse_base_helper_0(int)
    1401:1410:public static final int parse_base_helper_2(int)
    1416:1429:public static final int parse_base_helper_3(int)
    1435:1438:public static final int parse_base_helper_4(int)
    1446:9528:public static final void parse_prefix(lupos.parser.turtle.ParserContext,kotlin.jvm.functions.Function0)
    1645:1648:public static final int parse_prefix_helper_2(int)
    1656:9716:public static final void parse_prefix2(lupos.parser.turtle.ParserContext,kotlin.jvm.functions.Function0)
    1821:1824:public static final int parse_prefix2_helper_0(int)
    1872:1881:public static final int parse_prefix2_helper_2(int)
    1887:1900:public static final int parse_prefix2_helper_3(int)
    1906:1909:public static final int parse_prefix2_helper_4(int)
    1919:9904:public static final void parse_predicate(lupos.parser.turtle.ParserContext,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0)
    2244:2253:public static final int parse_predicate_helper_2(int)
    2259:2272:public static final int parse_predicate_helper_3(int)
    2278:2281:public static final int parse_predicate_helper_4(int)
    2365:2368:public static final int parse_predicate_helper_6(int)
    2386:13134:public static final void parse_obj(lupos.parser.turtle.ParserContext,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0)
    5785:5794:public static final int parse_obj_helper_2(int)
    5800:5813:public static final int parse_obj_helper_3(int)
    5819:5822:public static final int parse_obj_helper_4(int)
    5906:5909:public static final int parse_obj_helper_6(int)
    6091:6094:public static final int parse_obj_helper_11(int)
    6184:6187:public static final int parse_obj_helper_15(int)
    6217:6230:public static final int parse_obj_helper_17(int)
    6236:6249:public static final int parse_obj_helper_18(int)
    6255:6264:public static final int parse_obj_helper_19(int)
    6270:6283:public static final int parse_obj_helper_20(int)
    6289:6294:public static final int parse_obj_helper_21(int)
    6300:6309:public static final int parse_obj_helper_22(int)
    6315:6318:public static final int parse_obj_helper_23(int)
    6324:6327:public static final int parse_obj_helper_24(int)
    6333:6336:public static final int parse_obj_helper_25(int)
    6342:6345:public static final int parse_obj_helper_26(int)
    6351:6354:public static final int parse_obj_helper_27(int)
    6360:6363:public static final int parse_obj_helper_28(int)
    6369:6372:public static final int parse_obj_helper_29(int)
    6382:13150:public static final void parse_triple_end(lupos.parser.turtle.ParserContext,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0)
    6411:6424:public static final int parse_triple_end_helper_0(int)
    6436:13264:public static final void parse_triple_end_or_object_iri(lupos.parser.turtle.ParserContext,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0)
    6881:6894:public static final int parse_triple_end_or_object_iri_helper_2(int)
    6941:13298:public static final void parse_triple_end_or_object_string(lupos.parser.turtle.ParserContext,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0)
    7092:7101:public static final int parse_triple_end_or_object_string_helper_1(int)
    7107:7110:public static final int parse_triple_end_or_object_string_helper_2(int)
    7116:7129:public static final int parse_triple_end_or_object_string_helper_3(int)
    7135:7138:public static final int parse_triple_end_or_object_string_helper_4(int)
    7147:13486:public static final void parse_triple_end_or_object_string_typed(lupos.parser.turtle.ParserContext,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0)
    7465:7474:public static final int parse_triple_end_or_object_string_typed_helper_2(int)
    7480:7493:public static final int parse_triple_end_or_object_string_typed_helper_3(int)
    7499:7502:public static final int parse_triple_end_or_object_string_typed_helper_4(int)
    7586:7589:public static final int parse_triple_end_or_object_string_typed_helper_6(int)
    7601:13600:public static final void parse_triple_end_or_object_string_typed_iri(lupos.parser.turtle.ParserContext,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0)
    8046:8059:public static final int parse_triple_end_or_object_string_typed_iri_helper_2(int)
    8102:13714:public static final void parse_subject_iri_or_ws(lupos.parser.turtle.ParserContext,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0)
    8520:8533:public static final int parse_subject_iri_or_ws_helper_2(int)
    8576:13828:public static final void parse_predicate_iri_or_ws(lupos.parser.turtle.ParserContext,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0)
    8994:9007:public static final int parse_predicate_iri_or_ws_helper_2(int)
lupos.parser.turtle.Turtle2ParserStateExt
lupos.parser.turtle.Turtle2ParserStateKt
lupos.parser.turtle.TurtleParserWithDictionary
lupos.parser.turtle.TurtleParserWithStringTriples:
    private static final java.lang.String xsd
    public static final java.lang.String xsd_boolean
    public static final java.lang.String xsd_integer
    public static final java.lang.String xsd_decimal
    public static final java.lang.String xsd_double
    private static final java.lang.String rdf
    private static final java.lang.String nil
    public static final java.lang.String first
    private static final java.lang.String rest
    public static final java.lang.String nil_iri
    public static final java.lang.String first_iri
    public static final java.lang.String rest_iri
    public static final java.lang.String type_iri
lupos.parser.turtle.TurtleScanner:
    328:4152:private final lupos.parser.Token PNAME_LN_after_colon(java.lang.String,int)
    2:4317:private final lupos.parser.Token numberAfterDot(java.lang.String,int)
    449:4417:private final lupos.parser.Token numberAfterExp(java.lang.String,boolean,java.lang.String,char,int)
    478:4672:private final lupos.parser.Token dealWithString(char,int)
    531:544:private final boolean PN_CHARS_BASE(char)
    547:4686:private final boolean PN_CHARS_U(char)
    550:550:private final boolean DIGIT(char)
    554:4702:private final boolean VARNAMESECONDCHARANDLATER(char)
    561:4728:private final boolean PN_CHARS(char)
    564:4749:private final boolean PN_CHARS_U_or_DIGIT(char)
    567:594:private final boolean PN_LOCAL_ESC(char)
    597:606:private final boolean HEX(char)
lupos.result_format.EQueryResultToStreamExt:
    public static final int DEFAULT_STREAM
    public static final int EMPTYDICTIONARY_STREAM
    public static final int EMPTY_STREAM
    public static final int MEMORY_TABLE
    public static final int TURTLE_STREAM
    public static final int XML_ELEMENT
    public static final int XML_STREAM
    public static final int values_size
    public static final int values_mask
    public static final int values_mask_inversed
lupos.result_format.EQueryResultToStreamKt
lupos.result_format.QueryResultToEmptyStream:
    38:51:private final void writeAllRows(java.lang.String[],lupos.shared.operator.iterator.ColumnIterator[])
lupos.result_format.QueryResultToEmptyWithDictionaryStream:
    51:66:private final void writeAllRows(java.lang.String[],lupos.shared.operator.iterator.ColumnIterator[],lupos.shared.dictionary.IDictionary,lupos.shared.IMyOutputStream)
lupos.result_format.QueryResultToMemoryTable:
    44:177:private final void writeAllRows(java.lang.String[],lupos.shared.operator.iterator.ColumnIterator[],lupos.shared.dictionary.IDictionary,lupos.shared.MyThreadLock,lupos.shared.MemoryTable)
lupos.result_format.QueryResultToTurtleStream:
    96:410:private final void writeAllRows(java.lang.String[],lupos.shared.operator.iterator.ColumnIterator[],lupos.shared.dictionary.IDictionary,lupos.shared.MyThreadLock,lupos.shared.IMyOutputStream)
lupos.shared.AflCore
lupos.shared.AflCore$MyRandom
lupos.shared.AflCore$invoke$$inlined$launch$luposdate3000_shared$1
lupos.shared.AflCore$invoke$2
lupos.shared.AflCore$invoke$3
lupos.shared.AflCore$invoke$4
lupos.shared.AflCore$invoke$7
lupos.shared.AflCore$invoke$8
lupos.shared.AflCore$invoke$9
lupos.shared.AggregationExt:
    public static final int AVG
    public static final int COUNT
    public static final int GROUP_CONCAT
    public static final int MAX
    public static final int MIN
    public static final int SAMPLE
    public static final int SUM
    public static final int values_size
    public static final int values_mask
    public static final int values_mask_inversed
lupos.shared.AggregationKt
lupos.shared.BuiltInFunctionsExt:
    public static final int ABS
    public static final int BNODE
    public static final int BOUND
    public static final int CEIL
    public static final int COALESCE
    public static final int CONCAT
    public static final int CONTAINS
    public static final int DATATYPE
    public static final int DAY
    public static final int ENCODE_FOR_URI
    public static final int Exists
    public static final int FLOOR
    public static final int HOURS
    public static final int IF
    public static final int IRI
    public static final int LANG
    public static final int LANGMATCHES
    public static final int LCASE
    public static final int MD5
    public static final int MINUTES
    public static final int MONTH
    public static final int NOW
    public static final int NotExists
    public static final int RAND
    public static final int ROUND
    public static final int RegexExpression
    public static final int SECONDS
    public static final int SHA1
    public static final int SHA256
    public static final int SHA384
    public static final int SHA512
    public static final int STR
    public static final int STRAFTER
    public static final int STRBEFORE
    public static final int STRDT
    public static final int STRENDS
    public static final int STRLANG
    public static final int STRLEN
    public static final int STRSTARTS
    public static final int STRUUID
    public static final int StrReplaceExpression
    public static final int SubstringExpression
    public static final int TIMEZONE
    public static final int TZ
    public static final int UCASE
    public static final int URI
    public static final int UUID
    public static final int YEAR
    public static final int isBLANK
    public static final int isIRI
    public static final int isLITERAL
    public static final int isNUMERIC
    public static final int isURI
    public static final int sameTerm
    public static final int values_size
    public static final int values_mask
    public static final int values_mask_inversed
lupos.shared.BuiltInFunctionsKt
lupos.shared.CanNotCastBNodeToDecimalException
lupos.shared.CanNotCastBNodeToDoubleException
lupos.shared.CanNotCastBNodeToIntException
lupos.shared.CanNotCastBooleanToDecimalException
lupos.shared.CanNotCastBooleanToDoubleException
lupos.shared.CanNotCastBooleanToIntException
lupos.shared.CanNotCastDateTimeToDecimalException
lupos.shared.CanNotCastDateTimeToDoubleException
lupos.shared.CanNotCastDateTimeToIntException
lupos.shared.CanNotCastErrorToDecimalException
lupos.shared.CanNotCastErrorToDoubleException
lupos.shared.CanNotCastErrorToIntException
lupos.shared.CanNotCastIriToDecimalException
lupos.shared.CanNotCastIriToDoubleException
lupos.shared.CanNotCastIriToIntException
lupos.shared.CanNotCastLiteralToDecimalException
lupos.shared.CanNotCastLiteralToDoubleException
lupos.shared.CanNotCastLiteralToIntException
lupos.shared.CanNotCastUndefToDecimalException
lupos.shared.CanNotCastUndefToDoubleException
lupos.shared.CanNotCastUndefToIntException
lupos.shared.CodeGenerationAnnotation
lupos.shared.CodeGenerationAnnotationKSP
lupos.shared.ColumnIteratorChildIterator:
    36:101:public final void addChildColumnIteratorValue(long)
    44:60:public final void addChild(lupos.shared.operator.iterator.ColumnIterator)
    64:67:public final void closeOnNoMoreElements()
    71:72:public final void releaseValue(lupos.shared.operator.iterator.ColumnIterator)
    76:103:public final void _close()
lupos.shared.ColumnIteratorValue$Companion:
    26:29:public final lupos.shared.ColumnIteratorValue invoke$luposdate3000_shared(long)
lupos.shared.Config_Luposdate3000_Buffer_Manager_InmemoryKt
lupos.shared.Config_Luposdate3000_DictionaryKt
lupos.shared.Config_Luposdate3000_EndpointKt
lupos.shared.Config_Luposdate3000_Endpoint_Launcher_Java_SocketsKt
lupos.shared.Config_Luposdate3000_Jena_Wrapper_OffKt
lupos.shared.Config_Luposdate3000_KVKt
lupos.shared.Config_Luposdate3000_Launch_EndpointKt
lupos.shared.Config_Luposdate3000_Operator_ArithmetikKt
lupos.shared.Config_Luposdate3000_Operator_BaseKt
lupos.shared.Config_Luposdate3000_Operator_FactoryKt
lupos.shared.Config_Luposdate3000_Operator_LogicalKt
lupos.shared.Config_Luposdate3000_Operator_PhysicalKt
lupos.shared.Config_Luposdate3000_Optimizer_AstKt
lupos.shared.Config_Luposdate3000_Optimizer_Distributed_QueryKt
lupos.shared.Config_Luposdate3000_Optimizer_LogicalKt
lupos.shared.Config_Luposdate3000_Optimizer_PhysicalKt
lupos.shared.Config_Luposdate3000_ParserKt
lupos.shared.Config_Luposdate3000_Result_FormatKt
lupos.shared.Config_Luposdate3000_SharedKt
lupos.shared.Config_Luposdate3000_Test_BuffermanagerKt
lupos.shared.Config_Luposdate3000_Triple_Store_Id_TripleKt
lupos.shared.Config_Luposdate3000_Triple_Store_ManagerKt
lupos.shared.Config_Luposdate3000_VKKt
lupos.shared.EGarbageCollectorExt
lupos.shared.EGarbageCollectorKt
lupos.shared.EGraphOperationTypeExt
lupos.shared.EGraphOperationTypeKt
lupos.shared.EGraphRefTypeExt
lupos.shared.EGraphRefTypeKt
lupos.shared.EIndexPatternExt:
    public static final int OPS
    public static final int OP_S
    public static final int OSP
    public static final int OS_P
    public static final int O_PS
    public static final int O_SP
    public static final int POS
    public static final int PO_S
    public static final int PSO
    public static final int PS_O
    public static final int P_OS
    public static final int P_SO
    public static final int SOP
    public static final int SO_P
    public static final int SPO
    public static final int SP_O
    public static final int S_OP
    public static final int S_PO
    public static final int values_size
    public static final int values_mask
    public static final int values_mask_inversed
lupos.shared.EIndexPatternKt
lupos.shared.EModifyTypeExt:
    public static final int DELETE
    public static final int INSERT
    public static final int values_size
    public static final int values_mask
    public static final int values_mask_inversed
lupos.shared.EModifyTypeKt
lupos.shared.EOperatingSystemExt
lupos.shared.EOperatingSystemKt
lupos.shared.EOperatorIDExt
lupos.shared.EOperatorIDKt
lupos.shared.EPOPDebugModeExt
lupos.shared.EPOPDebugModeKt
lupos.shared.EPartitionModeExt:
    public static final int None
    public static final int Process
    public static final int Thread
    public static final int values_size
    public static final int values_mask
    public static final int values_mask_inversed
lupos.shared.EPartitionModeKt
lupos.shared.ESortPriorityExt
lupos.shared.ESortPriorityKt
lupos.shared.ESortTypeExt:
    public static final int ASC
    public static final int DESC
    public static final int FAST
    public static final int values_size
    public static final int values_mask
    public static final int values_mask_inversed
lupos.shared.ESortTypeKt
lupos.shared.ETripleComponentTypeExt
lupos.shared.ETripleComponentTypeKt
lupos.shared.ETripleIndexTypeExt
lupos.shared.ETripleIndexTypeKt
lupos.shared.ExceptionsKt
lupos.shared.GraphNameAlreadyExistsDuringCreateException
lupos.shared.GraphNameNotExistsDuringDeleteException
lupos.shared.GraphNameNotFoundException
lupos.shared.IBufferManager:
    public abstract int getNumberOfAllocatedPages()
    public abstract int getNumberOfReferencedPages()
lupos.shared.IMyInputStream:
    public abstract int read(byte[],int,int)
    public abstract long readLong()
    public abstract java.lang.String readLine()
lupos.shared.IMyOutputStream:
    public abstract void print(int)
lupos.shared.IQuery:
    public abstract java.util.Map getDependenciesMapTopDown()
    public abstract lupos.shared.operator.IOPBase getRoot()
    public abstract java.util.Map getOperatorgraphParts()
    public abstract java.util.Map getOperatorgraphPartsToHostMap()
lupos.shared.ITripleStoreDescription:
    public abstract java.lang.String toMetaString()
lupos.shared.ITripleStoreIndexDescription:
    public abstract lupos.shared.XMLElement toXMLElement()
lupos.shared.ITripleStoreLocalBase
lupos.shared.JenaBugException
lupos.shared.LuposGraphNameKt
lupos.shared.LuposHostnameKt
lupos.shared.LuposStoreKeyKt
lupos.shared.Luposdate3000InstanceKt
lupos.shared.MemoryTable$Companion:
    203:220:public final lupos.shared.MemoryTable merge$luposdate3000_shared(lupos.shared.MemoryTable,lupos.shared.MemoryTable)
lupos.shared.MyInputStreamFromByteArray
lupos.shared.MyPrintWriterModeExt
lupos.shared.MyPrintWriterModeKt
lupos.shared.MyThreadLock:
    33:33:public final long getUUID()
    37:38:public final void lock()
    42:43:public final void unlock()
    47:47:public final boolean tryLock()
    51:64:public final java.lang.Object withLock(kotlin.jvm.functions.Function0)
lupos.shared.OPVisualEdge:
    19:19:public final int getFrom()
    19:19:public final int getTo()
    19:19:public final int getWidth()
lupos.shared.OPVisualNode:
    19:19:public final int getId()
    19:19:public final java.lang.String getLabel()
lupos.shared.OperatorGraphToLatex:
    20:20:public static synthetic java.lang.String invoke$default(lupos.shared.OperatorGraphToLatex,java.lang.String,java.lang.String,int,java.lang.Object)
lupos.shared.SortHelper:
    public final java.lang.String component1()
    public final int component2()
    public final lupos.shared.SortHelper copy(java.lang.String,int)
    public static synthetic lupos.shared.SortHelper copy$default(lupos.shared.SortHelper,java.lang.String,int,int,java.lang.Object)
lupos.shared.TriplePatternsContainingTheSameVariableTwiceNotImplementedException
lupos.shared.TripleStoreFeatureKt
lupos.shared.TripleStoreManager:
    public static final java.lang.String DEFAULT_GRAPH_NAME
    public abstract void delete()
    public abstract void resetGraph(lupos.shared.IQuery,java.lang.String)
lupos.shared.UnknownManifestException
lupos.shared.UnknownOperatorTypeInXMLException
lupos.shared.ValueBnode:
    137:137:public lupos.shared.XMLElement toXMLElement(boolean)
    146:146:public java.lang.Void toDouble()
    147:147:public java.lang.Void toDecimal()
    148:148:public java.lang.Void toInt()
    158:158:public lupos.shared.ValueDefinition plus(com.ionspin.kotlin.bignum.integer.BigInteger)
    159:159:public lupos.shared.ValueDefinition plus(lupos.shared.ValueInteger)
    160:160:public lupos.shared.ValueDefinition plus(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    161:161:public lupos.shared.ValueDefinition plus(lupos.shared.ValueBoolean)
    162:162:public lupos.shared.ValueDefinition plus(lupos.shared.ValueDefinition)
    164:164:public lupos.shared.ValueDefinition minus(com.ionspin.kotlin.bignum.integer.BigInteger)
    165:165:public lupos.shared.ValueDefinition minus(lupos.shared.ValueInteger)
    166:166:public lupos.shared.ValueDefinition minus(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    167:167:public lupos.shared.ValueDefinition minus(lupos.shared.ValueBoolean)
    168:168:public lupos.shared.ValueDefinition minus(lupos.shared.ValueDefinition)
    170:170:public lupos.shared.ValueDefinition times(com.ionspin.kotlin.bignum.integer.BigInteger)
    171:171:public lupos.shared.ValueDefinition times(lupos.shared.ValueInteger)
    172:172:public lupos.shared.ValueDefinition times(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    173:173:public lupos.shared.ValueDefinition times(lupos.shared.ValueBoolean)
    174:174:public lupos.shared.ValueDefinition times(lupos.shared.ValueDefinition)
    176:176:public lupos.shared.ValueDefinition div(com.ionspin.kotlin.bignum.integer.BigInteger)
    177:177:public lupos.shared.ValueDefinition div(lupos.shared.ValueInteger)
    178:178:public lupos.shared.ValueDefinition div(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    179:179:public lupos.shared.ValueDefinition div(lupos.shared.ValueBoolean)
    180:180:public lupos.shared.ValueDefinition div(lupos.shared.ValueDefinition)
    182:182:public int compareTo(com.ionspin.kotlin.bignum.integer.BigInteger)
    183:183:public int compareTo(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    184:184:public int compareTo(java.lang.String)
    185:185:public int compareTo(lupos.shared.ValueStringBase)
    186:186:public int compareTo(lupos.shared.ValueIri)
    187:187:public int compareTo(lupos.shared.ValueBnode)
    188:188:public int compareTo(lupos.shared.ValueBoolean)
    136:136:public bridge synthetic double toDouble()
    136:136:public bridge synthetic com.ionspin.kotlin.bignum.decimal.BigDecimal toDecimal()
    136:136:public bridge synthetic com.ionspin.kotlin.bignum.integer.BigInteger toInt()
lupos.shared.ValueBoolean:
    207:207:public lupos.shared.XMLElement toXMLElement(boolean)
    218:218:public java.lang.Void toDouble()
    219:219:public java.lang.Void toDecimal()
    220:220:public java.lang.Void toInt()
    238:241:public lupos.shared.ValueDefinition plus(com.ionspin.kotlin.bignum.integer.BigInteger)
    246:249:public lupos.shared.ValueDefinition plus(lupos.shared.ValueInteger)
    254:257:public lupos.shared.ValueDefinition plus(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    262:272:public lupos.shared.ValueDefinition plus(lupos.shared.ValueBoolean)
    278:297:public lupos.shared.ValueDefinition plus(lupos.shared.ValueDefinition)
    301:304:public lupos.shared.ValueDefinition minus(com.ionspin.kotlin.bignum.integer.BigInteger)
    309:312:public lupos.shared.ValueDefinition minus(lupos.shared.ValueInteger)
    317:320:public lupos.shared.ValueDefinition minus(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    325:335:public lupos.shared.ValueDefinition minus(lupos.shared.ValueBoolean)
    341:360:public lupos.shared.ValueDefinition minus(lupos.shared.ValueDefinition)
    364:367:public lupos.shared.ValueDefinition times(com.ionspin.kotlin.bignum.integer.BigInteger)
    372:375:public lupos.shared.ValueDefinition times(lupos.shared.ValueInteger)
    380:383:public lupos.shared.ValueDefinition times(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    388:395:public lupos.shared.ValueDefinition times(lupos.shared.ValueBoolean)
    400:419:public lupos.shared.ValueDefinition times(lupos.shared.ValueDefinition)
    423:433:public lupos.shared.ValueDefinition div(com.ionspin.kotlin.bignum.integer.BigInteger)
    439:449:public lupos.shared.ValueDefinition div(lupos.shared.ValueInteger)
    455:465:public lupos.shared.ValueDefinition div(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    471:481:public lupos.shared.ValueDefinition div(lupos.shared.ValueBoolean)
    487:522:public lupos.shared.ValueDefinition div(lupos.shared.ValueDefinition)
    525:525:public int compareTo(com.ionspin.kotlin.bignum.integer.BigInteger)
    526:526:public int compareTo(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    527:527:public int compareTo(java.lang.String)
    528:528:public int compareTo(lupos.shared.ValueStringBase)
    529:529:public int compareTo(lupos.shared.ValueIri)
    530:530:public int compareTo(lupos.shared.ValueBnode)
    532:537:public int compareTo(lupos.shared.ValueBoolean)
    191:191:public bridge synthetic double toDouble()
    191:191:public bridge synthetic com.ionspin.kotlin.bignum.decimal.BigDecimal toDecimal()
    191:191:public bridge synthetic com.ionspin.kotlin.bignum.integer.BigInteger toInt()
lupos.shared.ValueDateTime:
    1685:1685:public lupos.shared.ValueDefinition plus(com.ionspin.kotlin.bignum.integer.BigInteger)
    1686:1686:public lupos.shared.ValueDefinition plus(lupos.shared.ValueInteger)
    1687:1687:public lupos.shared.ValueDefinition plus(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    1688:1688:public lupos.shared.ValueDefinition plus(lupos.shared.ValueBoolean)
    1689:1689:public lupos.shared.ValueDefinition plus(lupos.shared.ValueDefinition)
    1690:1690:public lupos.shared.ValueDefinition minus(com.ionspin.kotlin.bignum.integer.BigInteger)
    1691:1691:public lupos.shared.ValueDefinition minus(lupos.shared.ValueInteger)
    1692:1692:public lupos.shared.ValueDefinition minus(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    1693:1693:public lupos.shared.ValueDefinition minus(lupos.shared.ValueBoolean)
    1694:1694:public lupos.shared.ValueDefinition minus(lupos.shared.ValueDefinition)
    1695:1695:public lupos.shared.ValueDefinition times(com.ionspin.kotlin.bignum.integer.BigInteger)
    1696:1696:public lupos.shared.ValueDefinition times(lupos.shared.ValueInteger)
    1697:1697:public lupos.shared.ValueDefinition times(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    1698:1698:public lupos.shared.ValueDefinition times(lupos.shared.ValueBoolean)
    1699:1699:public lupos.shared.ValueDefinition times(lupos.shared.ValueDefinition)
    1700:1700:public lupos.shared.ValueDefinition div(com.ionspin.kotlin.bignum.integer.BigInteger)
    1701:1701:public lupos.shared.ValueDefinition div(lupos.shared.ValueInteger)
    1702:1702:public lupos.shared.ValueDefinition div(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    1703:1703:public lupos.shared.ValueDefinition div(lupos.shared.ValueBoolean)
    1704:1704:public lupos.shared.ValueDefinition div(lupos.shared.ValueDefinition)
    1705:1705:public int compareTo(com.ionspin.kotlin.bignum.integer.BigInteger)
    1706:1706:public int compareTo(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    1707:1707:public int compareTo(java.lang.String)
    1708:1708:public int compareTo(lupos.shared.ValueStringBase)
    1709:1709:public int compareTo(lupos.shared.ValueBoolean)
    1710:1710:public int compareTo(lupos.shared.ValueIri)
    1711:1711:public int compareTo(lupos.shared.ValueBnode)
    1864:1870:public final java.lang.String getTZ()
    1874:1880:public final java.lang.String getTimeZone()
    1898:1898:public lupos.shared.XMLElement toXMLElement(boolean)
    1909:1909:public double toDouble()
    1910:1910:public com.ionspin.kotlin.bignum.decimal.BigDecimal toDecimal()
    1911:1911:public com.ionspin.kotlin.bignum.integer.BigInteger toInt()
lupos.shared.ValueDecimal:
    749:749:public lupos.shared.XMLElement toXMLElement(boolean)
    761:761:public double toDouble()
    762:762:public com.ionspin.kotlin.bignum.decimal.BigDecimal toDecimal()
    763:763:public com.ionspin.kotlin.bignum.integer.BigInteger toInt()
    782:782:public lupos.shared.ValueDefinition plus(com.ionspin.kotlin.bignum.integer.BigInteger)
    783:783:public lupos.shared.ValueDefinition plus(lupos.shared.ValueInteger)
    784:784:public lupos.shared.ValueDefinition plus(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    786:789:public lupos.shared.ValueDefinition plus(lupos.shared.ValueBoolean)
    794:803:public lupos.shared.ValueDefinition plus(lupos.shared.ValueDefinition)
    806:806:public lupos.shared.ValueDefinition minus(com.ionspin.kotlin.bignum.integer.BigInteger)
    807:807:public lupos.shared.ValueDefinition minus(lupos.shared.ValueInteger)
    808:808:public lupos.shared.ValueDefinition minus(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    810:813:public lupos.shared.ValueDefinition minus(lupos.shared.ValueBoolean)
    818:1915:public lupos.shared.ValueDefinition minus(lupos.shared.ValueDefinition)
    830:830:public lupos.shared.ValueDefinition times(com.ionspin.kotlin.bignum.integer.BigInteger)
    831:831:public lupos.shared.ValueDefinition times(lupos.shared.ValueInteger)
    832:832:public lupos.shared.ValueDefinition times(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    834:837:public lupos.shared.ValueDefinition times(lupos.shared.ValueBoolean)
    842:1916:public lupos.shared.ValueDefinition times(lupos.shared.ValueDefinition)
    855:865:public lupos.shared.ValueDefinition div(com.ionspin.kotlin.bignum.integer.BigInteger)
    871:881:public lupos.shared.ValueDefinition div(lupos.shared.ValueInteger)
    887:897:public lupos.shared.ValueDefinition div(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    903:913:public lupos.shared.ValueDefinition div(lupos.shared.ValueBoolean)
    919:1920:public lupos.shared.ValueDefinition div(lupos.shared.ValueDefinition)
    943:943:public int compareTo(com.ionspin.kotlin.bignum.integer.BigInteger)
    944:944:public int compareTo(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    945:945:public int compareTo(java.lang.String)
    946:946:public int compareTo(lupos.shared.ValueStringBase)
    947:947:public int compareTo(lupos.shared.ValueIri)
    948:948:public int compareTo(lupos.shared.ValueBnode)
    949:949:public int compareTo(lupos.shared.ValueBoolean)
lupos.shared.ValueDefinition:
    public abstract lupos.shared.XMLElement toXMLElement(boolean)
    public abstract double toDouble()
    public abstract com.ionspin.kotlin.bignum.decimal.BigDecimal toDecimal()
    public abstract com.ionspin.kotlin.bignum.integer.BigInteger toInt()
    public abstract int compareTo(com.ionspin.kotlin.bignum.integer.BigInteger)
    public abstract int compareTo(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    public abstract int compareTo(java.lang.String)
    public abstract int compareTo(lupos.shared.ValueStringBase)
    public abstract int compareTo(lupos.shared.ValueIri)
    public abstract int compareTo(lupos.shared.ValueBnode)
    public abstract int compareTo(lupos.shared.ValueBoolean)
    public abstract lupos.shared.ValueDefinition plus(com.ionspin.kotlin.bignum.integer.BigInteger)
    public abstract lupos.shared.ValueDefinition plus(lupos.shared.ValueInteger)
    public abstract lupos.shared.ValueDefinition plus(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    public abstract lupos.shared.ValueDefinition plus(lupos.shared.ValueBoolean)
    public abstract lupos.shared.ValueDefinition plus(lupos.shared.ValueDefinition)
    public abstract lupos.shared.ValueDefinition minus(com.ionspin.kotlin.bignum.integer.BigInteger)
    public abstract lupos.shared.ValueDefinition minus(lupos.shared.ValueInteger)
    public abstract lupos.shared.ValueDefinition minus(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    public abstract lupos.shared.ValueDefinition minus(lupos.shared.ValueBoolean)
    public abstract lupos.shared.ValueDefinition minus(lupos.shared.ValueDefinition)
    public abstract lupos.shared.ValueDefinition times(com.ionspin.kotlin.bignum.integer.BigInteger)
    public abstract lupos.shared.ValueDefinition times(lupos.shared.ValueInteger)
    public abstract lupos.shared.ValueDefinition times(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    public abstract lupos.shared.ValueDefinition times(lupos.shared.ValueBoolean)
    public abstract lupos.shared.ValueDefinition times(lupos.shared.ValueDefinition)
    public abstract lupos.shared.ValueDefinition div(com.ionspin.kotlin.bignum.integer.BigInteger)
    public abstract lupos.shared.ValueDefinition div(lupos.shared.ValueInteger)
    public abstract lupos.shared.ValueDefinition div(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    public abstract lupos.shared.ValueDefinition div(lupos.shared.ValueBoolean)
    public abstract lupos.shared.ValueDefinition div(lupos.shared.ValueDefinition)
    130:130:public final java.lang.String toSparql()
lupos.shared.ValueDefinition$Companion:
    69:69:public final lupos.shared.ValueDefinition convertToValueDefinition(boolean)
    73:73:public final lupos.shared.ValueDefinition convertToValueDefinition(com.ionspin.kotlin.bignum.integer.BigInteger)
    77:77:public final lupos.shared.ValueDefinition convertToValueDefinition(lupos.shared.ValueDefinition)
    81:81:public final lupos.shared.ValueDefinition convertToValueDefinition(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    85:85:public final lupos.shared.ValueDefinition convertToValueDefinition(java.lang.String)
    89:1916:public final lupos.shared.ValueDefinition invoke(java.lang.String)
lupos.shared.ValueDefinitionsKt
lupos.shared.ValueDouble:
    953:953:public lupos.shared.XMLElement toXMLElement(boolean)
    965:965:public double toDouble()
    966:966:public com.ionspin.kotlin.bignum.decimal.BigDecimal toDecimal()
    967:967:public com.ionspin.kotlin.bignum.integer.BigInteger toInt()
    984:984:public lupos.shared.ValueDefinition plus(com.ionspin.kotlin.bignum.integer.BigInteger)
    985:985:public lupos.shared.ValueDefinition plus(lupos.shared.ValueInteger)
    986:986:public lupos.shared.ValueDefinition plus(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    988:991:public lupos.shared.ValueDefinition plus(lupos.shared.ValueBoolean)
    996:1005:public lupos.shared.ValueDefinition plus(lupos.shared.ValueDefinition)
    1008:1008:public lupos.shared.ValueDefinition minus(com.ionspin.kotlin.bignum.integer.BigInteger)
    1009:1009:public lupos.shared.ValueDefinition minus(lupos.shared.ValueInteger)
    1010:1010:public lupos.shared.ValueDefinition minus(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    1012:1015:public lupos.shared.ValueDefinition minus(lupos.shared.ValueBoolean)
    1020:1029:public lupos.shared.ValueDefinition minus(lupos.shared.ValueDefinition)
    1032:1032:public lupos.shared.ValueDefinition times(com.ionspin.kotlin.bignum.integer.BigInteger)
    1033:1033:public lupos.shared.ValueDefinition times(lupos.shared.ValueInteger)
    1034:1034:public lupos.shared.ValueDefinition times(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    1036:1039:public lupos.shared.ValueDefinition times(lupos.shared.ValueBoolean)
    1044:1053:public lupos.shared.ValueDefinition times(lupos.shared.ValueDefinition)
    1057:1067:public lupos.shared.ValueDefinition div(com.ionspin.kotlin.bignum.integer.BigInteger)
    1073:1083:public lupos.shared.ValueDefinition div(lupos.shared.ValueInteger)
    1089:1099:public lupos.shared.ValueDefinition div(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    1105:1115:public lupos.shared.ValueDefinition div(lupos.shared.ValueBoolean)
    1121:1140:public lupos.shared.ValueDefinition div(lupos.shared.ValueDefinition)
    1143:1143:public int compareTo(com.ionspin.kotlin.bignum.integer.BigInteger)
    1144:1144:public int compareTo(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    1145:1145:public int compareTo(java.lang.String)
    1146:1146:public int compareTo(lupos.shared.ValueStringBase)
    1147:1147:public int compareTo(lupos.shared.ValueIri)
    1148:1148:public int compareTo(lupos.shared.ValueBnode)
    1149:1149:public int compareTo(lupos.shared.ValueBoolean)
lupos.shared.ValueError:
    590:590:public lupos.shared.XMLElement toXMLElement(boolean)
    593:593:public java.lang.Void toDouble()
    594:594:public java.lang.Void toDecimal()
    595:595:public java.lang.Void toInt()
    599:599:public lupos.shared.ValueDefinition plus(lupos.shared.ValueDefinition)
    600:600:public lupos.shared.ValueDefinition plus(com.ionspin.kotlin.bignum.integer.BigInteger)
    601:601:public lupos.shared.ValueDefinition plus(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    602:602:public lupos.shared.ValueDefinition plus(lupos.shared.ValueBoolean)
    603:603:public lupos.shared.ValueDefinition plus(lupos.shared.ValueInteger)
    604:604:public lupos.shared.ValueDefinition minus(com.ionspin.kotlin.bignum.integer.BigInteger)
    605:605:public lupos.shared.ValueDefinition minus(lupos.shared.ValueInteger)
    606:606:public lupos.shared.ValueDefinition minus(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    607:607:public lupos.shared.ValueDefinition minus(lupos.shared.ValueBoolean)
    608:608:public lupos.shared.ValueDefinition minus(lupos.shared.ValueDefinition)
    609:609:public lupos.shared.ValueDefinition times(lupos.shared.ValueDefinition)
    610:610:public lupos.shared.ValueDefinition times(com.ionspin.kotlin.bignum.integer.BigInteger)
    611:611:public lupos.shared.ValueDefinition times(lupos.shared.ValueInteger)
    612:612:public lupos.shared.ValueDefinition times(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    613:613:public lupos.shared.ValueDefinition times(lupos.shared.ValueBoolean)
    614:614:public lupos.shared.ValueDefinition div(lupos.shared.ValueDefinition)
    615:615:public lupos.shared.ValueDefinition div(com.ionspin.kotlin.bignum.integer.BigInteger)
    616:616:public lupos.shared.ValueDefinition div(lupos.shared.ValueInteger)
    617:617:public lupos.shared.ValueDefinition div(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    618:618:public lupos.shared.ValueDefinition div(lupos.shared.ValueBoolean)
    619:619:public int compareTo(com.ionspin.kotlin.bignum.integer.BigInteger)
    620:620:public int compareTo(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    621:621:public int compareTo(java.lang.String)
    622:622:public int compareTo(lupos.shared.ValueStringBase)
    623:623:public int compareTo(lupos.shared.ValueIri)
    624:624:public int compareTo(lupos.shared.ValueBnode)
    625:625:public int compareTo(lupos.shared.ValueBoolean)
    589:589:public bridge synthetic double toDouble()
    589:589:public bridge synthetic com.ionspin.kotlin.bignum.decimal.BigDecimal toDecimal()
    589:589:public bridge synthetic com.ionspin.kotlin.bignum.integer.BigInteger toInt()
lupos.shared.ValueFloat:
    1153:1153:public lupos.shared.XMLElement toXMLElement(boolean)
    1165:1165:public com.ionspin.kotlin.bignum.decimal.BigDecimal toDecimal()
    1166:1166:public double toDouble()
    1167:1167:public com.ionspin.kotlin.bignum.integer.BigInteger toInt()
    1186:1186:public lupos.shared.ValueDefinition plus(com.ionspin.kotlin.bignum.integer.BigInteger)
    1187:1187:public lupos.shared.ValueDefinition plus(lupos.shared.ValueInteger)
    1188:1188:public lupos.shared.ValueDefinition plus(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    1190:1193:public lupos.shared.ValueDefinition plus(lupos.shared.ValueBoolean)
    1198:1207:public lupos.shared.ValueDefinition plus(lupos.shared.ValueDefinition)
    1210:1210:public lupos.shared.ValueDefinition minus(com.ionspin.kotlin.bignum.integer.BigInteger)
    1211:1211:public lupos.shared.ValueDefinition minus(lupos.shared.ValueInteger)
    1212:1212:public lupos.shared.ValueDefinition minus(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    1214:1217:public lupos.shared.ValueDefinition minus(lupos.shared.ValueBoolean)
    1222:1231:public lupos.shared.ValueDefinition minus(lupos.shared.ValueDefinition)
    1234:1234:public lupos.shared.ValueDefinition times(com.ionspin.kotlin.bignum.integer.BigInteger)
    1235:1235:public lupos.shared.ValueDefinition times(lupos.shared.ValueInteger)
    1236:1236:public lupos.shared.ValueDefinition times(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    1238:1241:public lupos.shared.ValueDefinition times(lupos.shared.ValueBoolean)
    1246:1255:public lupos.shared.ValueDefinition times(lupos.shared.ValueDefinition)
    1259:1269:public lupos.shared.ValueDefinition div(com.ionspin.kotlin.bignum.integer.BigInteger)
    1275:1285:public lupos.shared.ValueDefinition div(lupos.shared.ValueInteger)
    1291:1301:public lupos.shared.ValueDefinition div(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    1307:1317:public lupos.shared.ValueDefinition div(lupos.shared.ValueBoolean)
    1323:1342:public lupos.shared.ValueDefinition div(lupos.shared.ValueDefinition)
    1345:1345:public int compareTo(com.ionspin.kotlin.bignum.integer.BigInteger)
    1346:1346:public int compareTo(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    1347:1347:public int compareTo(java.lang.String)
    1348:1348:public int compareTo(lupos.shared.ValueStringBase)
    1349:1349:public int compareTo(lupos.shared.ValueIri)
    1350:1350:public int compareTo(lupos.shared.ValueBnode)
    1351:1351:public int compareTo(lupos.shared.ValueBoolean)
lupos.shared.ValueInteger:
    1395:1395:public lupos.shared.XMLElement toXMLElement(boolean)
    1407:1407:public com.ionspin.kotlin.bignum.decimal.BigDecimal toDecimal()
    1408:1408:public double toDouble()
    1409:1409:public com.ionspin.kotlin.bignum.integer.BigInteger toInt()
    1428:1428:public lupos.shared.ValueDefinition plus(com.ionspin.kotlin.bignum.integer.BigInteger)
    1429:1429:public lupos.shared.ValueDefinition plus(lupos.shared.ValueInteger)
    1430:1430:public lupos.shared.ValueDefinition plus(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    1432:1435:public lupos.shared.ValueDefinition plus(lupos.shared.ValueBoolean)
    1440:1915:public lupos.shared.ValueDefinition plus(lupos.shared.ValueDefinition)
    1454:1454:public lupos.shared.ValueDefinition minus(com.ionspin.kotlin.bignum.integer.BigInteger)
    1455:1455:public lupos.shared.ValueDefinition minus(lupos.shared.ValueInteger)
    1456:1456:public lupos.shared.ValueDefinition minus(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    1458:1461:public lupos.shared.ValueDefinition minus(lupos.shared.ValueBoolean)
    1466:1477:public lupos.shared.ValueDefinition minus(lupos.shared.ValueDefinition)
    1480:1480:public lupos.shared.ValueDefinition times(com.ionspin.kotlin.bignum.integer.BigInteger)
    1481:1481:public lupos.shared.ValueDefinition times(lupos.shared.ValueInteger)
    1482:1482:public lupos.shared.ValueDefinition times(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    1484:1487:public lupos.shared.ValueDefinition times(lupos.shared.ValueBoolean)
    1492:1503:public lupos.shared.ValueDefinition times(lupos.shared.ValueDefinition)
    1507:1517:public lupos.shared.ValueDefinition div(com.ionspin.kotlin.bignum.integer.BigInteger)
    1523:1533:public lupos.shared.ValueDefinition div(lupos.shared.ValueInteger)
    1539:1549:public lupos.shared.ValueDefinition div(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    1555:1565:public lupos.shared.ValueDefinition div(lupos.shared.ValueBoolean)
    1571:1590:public lupos.shared.ValueDefinition div(lupos.shared.ValueDefinition)
    1594:1594:public int compareTo(com.ionspin.kotlin.bignum.integer.BigInteger)
    1598:1598:public int compareTo(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    1601:1601:public int compareTo(java.lang.String)
    1602:1602:public int compareTo(lupos.shared.ValueStringBase)
    1603:1603:public int compareTo(lupos.shared.ValueIri)
    1604:1604:public int compareTo(lupos.shared.ValueBnode)
    1605:1605:public int compareTo(lupos.shared.ValueBoolean)
lupos.shared.ValueIri:
    1609:1609:public lupos.shared.XMLElement toXMLElement(boolean)
    1619:1619:public double toDouble()
    1620:1620:public com.ionspin.kotlin.bignum.decimal.BigDecimal toDecimal()
    1621:1621:public com.ionspin.kotlin.bignum.integer.BigInteger toInt()
    1625:1625:public lupos.shared.ValueDefinition plus(com.ionspin.kotlin.bignum.integer.BigInteger)
    1626:1626:public lupos.shared.ValueDefinition plus(lupos.shared.ValueInteger)
    1627:1627:public lupos.shared.ValueDefinition plus(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    1628:1628:public lupos.shared.ValueDefinition plus(lupos.shared.ValueBoolean)
    1629:1629:public lupos.shared.ValueDefinition plus(lupos.shared.ValueDefinition)
    1630:1630:public lupos.shared.ValueDefinition minus(com.ionspin.kotlin.bignum.integer.BigInteger)
    1631:1631:public lupos.shared.ValueDefinition minus(lupos.shared.ValueInteger)
    1632:1632:public lupos.shared.ValueDefinition minus(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    1633:1633:public lupos.shared.ValueDefinition minus(lupos.shared.ValueBoolean)
    1634:1634:public lupos.shared.ValueDefinition minus(lupos.shared.ValueDefinition)
    1635:1635:public lupos.shared.ValueDefinition times(com.ionspin.kotlin.bignum.integer.BigInteger)
    1636:1636:public lupos.shared.ValueDefinition times(lupos.shared.ValueInteger)
    1637:1637:public lupos.shared.ValueDefinition times(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    1638:1638:public lupos.shared.ValueDefinition times(lupos.shared.ValueBoolean)
    1639:1639:public lupos.shared.ValueDefinition times(lupos.shared.ValueDefinition)
    1640:1640:public lupos.shared.ValueDefinition div(com.ionspin.kotlin.bignum.integer.BigInteger)
    1641:1641:public lupos.shared.ValueDefinition div(lupos.shared.ValueInteger)
    1642:1642:public lupos.shared.ValueDefinition div(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    1643:1643:public lupos.shared.ValueDefinition div(lupos.shared.ValueBoolean)
    1644:1644:public lupos.shared.ValueDefinition div(lupos.shared.ValueDefinition)
    1645:1645:public int compareTo(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    1646:1646:public int compareTo(com.ionspin.kotlin.bignum.integer.BigInteger)
    1647:1647:public int compareTo(java.lang.String)
    1648:1648:public int compareTo(lupos.shared.ValueStringBase)
    1649:1649:public int compareTo(lupos.shared.ValueBnode)
    1650:1650:public int compareTo(lupos.shared.ValueIri)
    1651:1651:public int compareTo(lupos.shared.ValueBoolean)
lupos.shared.ValueLanguageTaggedLiteral:
    673:673:public lupos.shared.XMLElement toXMLElement(boolean)
lupos.shared.ValueSimpleLiteral:
    689:689:public lupos.shared.XMLElement toXMLElement(boolean)
lupos.shared.ValueStringBase:
    629:629:public lupos.shared.ValueDefinition plus(com.ionspin.kotlin.bignum.integer.BigInteger)
    630:630:public lupos.shared.ValueDefinition plus(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    631:631:public lupos.shared.ValueDefinition plus(lupos.shared.ValueInteger)
    632:632:public lupos.shared.ValueDefinition plus(lupos.shared.ValueBoolean)
    633:633:public lupos.shared.ValueDefinition plus(lupos.shared.ValueDefinition)
    634:634:public lupos.shared.ValueDefinition minus(com.ionspin.kotlin.bignum.integer.BigInteger)
    635:635:public lupos.shared.ValueDefinition minus(lupos.shared.ValueInteger)
    636:636:public lupos.shared.ValueDefinition minus(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    637:637:public lupos.shared.ValueDefinition minus(lupos.shared.ValueBoolean)
    638:638:public lupos.shared.ValueDefinition minus(lupos.shared.ValueDefinition)
    639:639:public lupos.shared.ValueDefinition times(lupos.shared.ValueDefinition)
    640:640:public lupos.shared.ValueDefinition times(com.ionspin.kotlin.bignum.integer.BigInteger)
    641:641:public lupos.shared.ValueDefinition times(lupos.shared.ValueInteger)
    642:642:public lupos.shared.ValueDefinition times(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    643:643:public lupos.shared.ValueDefinition times(lupos.shared.ValueBoolean)
    644:644:public lupos.shared.ValueDefinition div(lupos.shared.ValueDefinition)
    645:645:public lupos.shared.ValueDefinition div(com.ionspin.kotlin.bignum.integer.BigInteger)
    646:646:public lupos.shared.ValueDefinition div(lupos.shared.ValueInteger)
    647:647:public lupos.shared.ValueDefinition div(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    648:648:public lupos.shared.ValueDefinition div(lupos.shared.ValueBoolean)
    649:649:public int compareTo(com.ionspin.kotlin.bignum.integer.BigInteger)
    650:650:public int compareTo(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    651:651:public int compareTo(java.lang.String)
    652:652:public int compareTo(lupos.shared.ValueStringBase)
    653:653:public int compareTo(lupos.shared.ValueIri)
    654:654:public int compareTo(lupos.shared.ValueBnode)
    655:655:public int compareTo(lupos.shared.ValueBoolean)
    667:667:public java.lang.Void toDouble()
    668:668:public java.lang.Void toDecimal()
    669:669:public java.lang.Void toInt()
    628:628:public bridge synthetic double toDouble()
    628:628:public bridge synthetic com.ionspin.kotlin.bignum.decimal.BigDecimal toDecimal()
    628:628:public bridge synthetic com.ionspin.kotlin.bignum.integer.BigInteger toInt()
lupos.shared.ValueTypedLiteral:
    733:733:public lupos.shared.XMLElement toXMLElement(boolean)
lupos.shared.ValueUndef:
    544:544:public lupos.shared.XMLElement toXMLElement(boolean)
    554:554:public java.lang.Void toDouble()
    555:555:public java.lang.Void toDecimal()
    556:556:public java.lang.Void toInt()
    560:560:public lupos.shared.ValueDefinition plus(lupos.shared.ValueDefinition)
    561:561:public lupos.shared.ValueDefinition plus(com.ionspin.kotlin.bignum.integer.BigInteger)
    562:562:public lupos.shared.ValueDefinition plus(lupos.shared.ValueInteger)
    563:563:public lupos.shared.ValueDefinition plus(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    564:564:public lupos.shared.ValueDefinition plus(lupos.shared.ValueBoolean)
    565:565:public lupos.shared.ValueDefinition minus(com.ionspin.kotlin.bignum.integer.BigInteger)
    566:566:public lupos.shared.ValueDefinition minus(lupos.shared.ValueInteger)
    567:567:public lupos.shared.ValueDefinition minus(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    568:568:public lupos.shared.ValueDefinition minus(lupos.shared.ValueDefinition)
    569:569:public lupos.shared.ValueDefinition minus(lupos.shared.ValueBoolean)
    570:570:public lupos.shared.ValueDefinition times(lupos.shared.ValueDefinition)
    571:571:public lupos.shared.ValueDefinition times(com.ionspin.kotlin.bignum.integer.BigInteger)
    572:572:public lupos.shared.ValueDefinition times(lupos.shared.ValueInteger)
    573:573:public lupos.shared.ValueDefinition times(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    574:574:public lupos.shared.ValueDefinition times(lupos.shared.ValueBoolean)
    575:575:public lupos.shared.ValueDefinition div(lupos.shared.ValueDefinition)
    576:576:public lupos.shared.ValueDefinition div(com.ionspin.kotlin.bignum.integer.BigInteger)
    577:577:public lupos.shared.ValueDefinition div(lupos.shared.ValueInteger)
    578:578:public lupos.shared.ValueDefinition div(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    579:579:public lupos.shared.ValueDefinition div(lupos.shared.ValueBoolean)
    580:580:public int compareTo(com.ionspin.kotlin.bignum.integer.BigInteger)
    581:581:public int compareTo(com.ionspin.kotlin.bignum.decimal.BigDecimal)
    582:582:public int compareTo(java.lang.String)
    583:583:public int compareTo(lupos.shared.ValueStringBase)
    584:584:public int compareTo(lupos.shared.ValueIri)
    585:585:public int compareTo(lupos.shared.ValueBnode)
    586:586:public int compareTo(lupos.shared.ValueBoolean)
    543:543:public bridge synthetic double toDouble()
    543:543:public bridge synthetic com.ionspin.kotlin.bignum.decimal.BigDecimal toDecimal()
    543:543:public bridge synthetic com.ionspin.kotlin.bignum.integer.BigInteger toInt()
lupos.shared.XMLElement$Companion:
    28:67:public final void parseBindingFromString(lupos.shared.XMLElement,java.lang.String,java.lang.String)
    70:535:public final void parseBindingFromByteArrayWrapper(lupos.shared.XMLElement,lupos.shared.dynamicArray.ByteArrayWrapper,java.lang.String)
lupos.shared.dictionary.DictionaryNotImplemented:
    23:23:public boolean isInmemoryOnly()
    25:25:public void delete()
    28:28:public void forEachValue(lupos.shared.dynamicArray.ByteArrayWrapper,kotlin.jvm.functions.Function1)
lupos.shared.dictionary.EDictionaryTypeExt:
    public static final int InMemory
    public static final int KV
    public static final int values_size
    public static final int values_mask
    public static final int values_mask_inversed
lupos.shared.dictionary.EDictionaryTypeKt
lupos.shared.dictionary.IDictionary:
    public abstract void delete()
    public abstract boolean isInmemoryOnly()
    public abstract void forEachValue(lupos.shared.dynamicArray.ByteArrayWrapper,kotlin.jvm.functions.Function1)
lupos.shared.dynamicArray.ByteArrayWrapper:
    32:32:public ByteArrayWrapper(byte[])
lupos.shared.dynamicArray.IntArrayWrapper
lupos.shared.inline.BufferManagerPage:
    public static final int BUFFER_MANAGER_PAGE_SIZE_IN_BYTES
    26:177:public final byte[] create$luposdate3000_launch_endpoint()
    33:179:public final void copyInto$luposdate3000_launch_endpoint(byte[],byte[],int,int,int)
    39:181:public final void copyFrom$luposdate3000_launch_endpoint(byte[],byte[],int,int,int)
    45:182:public final int getPageID$luposdate3000_launch_endpoint(byte[])
    50:189:public final void setPageID$luposdate3000_launch_endpoint(byte[],int)
    56:193:public final void writeInt1$luposdate3000_launch_endpoint(byte[],int,int)
    62:198:public final void writeInt2$luposdate3000_launch_endpoint(byte[],int,int)
    68:204:public final void writeInt3$luposdate3000_launch_endpoint(byte[],int,int)
    74:211:public final void writeInt4$luposdate3000_launch_endpoint(byte[],int,int)
    80:245:public final void writeIntX$luposdate3000_launch_endpoint(byte[],int,int,int)
    100:256:public final void writeLong8$luposdate3000_launch_endpoint(byte[],int,long)
    106:262:public final void writeChar$luposdate3000_launch_endpoint(byte[],int,char)
    112:265:public final long readLong8$luposdate3000_launch_endpoint(byte[],int)
    118:268:public final int readInt4$luposdate3000_launch_endpoint(byte[],int)
    124:271:public final int readInt3$luposdate3000_launch_endpoint(byte[],int)
    130:274:public final int readInt2$luposdate3000_launch_endpoint(byte[],int)
    136:277:public final int readInt1$luposdate3000_launch_endpoint(byte[],int)
    142:297:public final int readIntX$luposdate3000_launch_endpoint(byte[],int,int)
    163:300:public final char readChar$luposdate3000_launch_endpoint(byte[],int)
lupos.shared.inline.ByteArrayHelper:
    22:23:public final void writeInt1$luposdate3000_launch_endpoint(byte[],int,int)
    27:29:public final void writeInt2$luposdate3000_launch_endpoint(byte[],int,int)
    33:36:public final void writeInt3$luposdate3000_launch_endpoint(byte[],int,int)
    40:44:public final void writeInt4$luposdate3000_launch_endpoint(byte[],int,int)
    48:316:public final void writeIntX$luposdate3000_launch_endpoint(byte[],int,int,int)
    68:325:public final void writeDouble8$luposdate3000_launch_endpoint(byte[],int,double)
    73:74:public final void writeLong1$luposdate3000_launch_endpoint(byte[],int,long)
    78:80:public final void writeLong2$luposdate3000_launch_endpoint(byte[],int,long)
    84:87:public final void writeLong3$luposdate3000_launch_endpoint(byte[],int,long)
    91:95:public final void writeLong4$luposdate3000_launch_endpoint(byte[],int,long)
    99:104:public final void writeLong5$luposdate3000_launch_endpoint(byte[],int,long)
    108:114:public final void writeLong6$luposdate3000_launch_endpoint(byte[],int,long)
    118:125:public final void writeLong7$luposdate3000_launch_endpoint(byte[],int,long)
    129:137:public final void writeLong8$luposdate3000_launch_endpoint(byte[],int,long)
    141:369:public final void writeLongX$luposdate3000_launch_endpoint(byte[],int,long,int)
    173:176:public final void writeChar$luposdate3000_launch_endpoint(byte[],int,char)
    180:370:public final double readDouble8$luposdate3000_launch_endpoint(byte[],int)
    185:185:public final long readLong1$luposdate3000_launch_endpoint(byte[],int)
    190:190:public final long readLong2$luposdate3000_launch_endpoint(byte[],int)
    195:195:public final long readLong3$luposdate3000_launch_endpoint(byte[],int)
    200:200:public final long readLong4$luposdate3000_launch_endpoint(byte[],int)
    205:205:public final long readLong5$luposdate3000_launch_endpoint(byte[],int)
    210:210:public final long readLong6$luposdate3000_launch_endpoint(byte[],int)
    215:215:public final long readLong7$luposdate3000_launch_endpoint(byte[],int)
    220:220:public final long readLong8$luposdate3000_launch_endpoint(byte[],int)
    225:378:public final long readLongX$luposdate3000_launch_endpoint(byte[],int,int)
    258:258:public final int readInt4$luposdate3000_launch_endpoint(byte[],int)
    263:263:public final int readInt3$luposdate3000_launch_endpoint(byte[],int)
    268:268:public final int readInt2$luposdate3000_launch_endpoint(byte[],int)
    273:273:public final int readInt1$luposdate3000_launch_endpoint(byte[],int)
    278:382:public final int readIntX$luposdate3000_launch_endpoint(byte[],int,int)
    299:299:public final char readChar$luposdate3000_launch_endpoint(byte[],int)
lupos.shared.inline.ColumnIteratorChildIteratorExt:
    25:71:public final long nextHelper(lupos.shared.ColumnIteratorChildIterator,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0)
lupos.shared.inline.ColumnIteratorQueueExt:
    25:29:public final void _close$luposdate3000_launch_endpoint(lupos.shared.operator.iterator.ColumnIteratorQueue)
    32:55:public final long nextHelper$luposdate3000_launch_endpoint(lupos.shared.operator.iterator.ColumnIteratorQueue,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0)
    62:65:public final void closeOnEmptyQueue$luposdate3000_launch_endpoint(lupos.shared.operator.iterator.ColumnIteratorQueue)
lupos.shared.inline.DateHelper:
    27:27:public final int year$luposdate3000_launch_endpoint()
    30:30:public final int month$luposdate3000_launch_endpoint()
    33:33:public final int day$luposdate3000_launch_endpoint()
    36:36:public final int hours$luposdate3000_launch_endpoint()
    39:39:public final int minutes$luposdate3000_launch_endpoint()
    42:42:public final int seconds$luposdate3000_launch_endpoint()
lupos.shared.inline.DictionaryHelper:
    46:46:public final int headerSize$luposdate3000_launch_endpoint()
    50:1065:public final void headerEncode$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,int,int)
    58:1067:public final int headerDecodeType$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    64:1069:public final int headerDecodeFlag$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    70:1085:public final void errorToByteArray$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    76:1101:public final void undefToByteArray$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    91:1187:public final void dateTimeToByteArray$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,java.lang.String)
    217:1289:public final void dateTimeToByteArray$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    223:1400:public final void dateTimeToByteArray$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,com.ionspin.kotlin.bignum.integer.BigInteger,int,int,int,int,com.ionspin.kotlin.bignum.decimal.BigDecimal,int,int,boolean)
    279:1409:public final com.ionspin.kotlin.bignum.integer.BigInteger byteArrayToDateTime_Year$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    309:1412:public final com.ionspin.kotlin.bignum.integer.BigInteger byteArrayToDateTime_Month$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    318:1415:public final com.ionspin.kotlin.bignum.integer.BigInteger byteArrayToDateTime_Day$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    328:1418:public final com.ionspin.kotlin.bignum.integer.BigInteger byteArrayToDateTime_Hours$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    339:1421:public final com.ionspin.kotlin.bignum.integer.BigInteger byteArrayToDateTime_Minutes$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    351:1433:public final com.ionspin.kotlin.bignum.decimal.BigDecimal byteArrayToDateTime_Seconds$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    383:1459:public final java.lang.String byteArrayToDateTimeAsTyped_Content$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    452:1464:public final java.lang.String byteArrayToDateTime_TZ$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    473:1469:public final java.lang.String byteArrayToDateTime_TimeZone$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    494:1497:public final void booleanToByteArray$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,boolean)
    505:1503:public final boolean byteArrayToBoolean$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    512:1539:public final void integerToByteArray$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,java.lang.String)
    517:1577:public final void integerToByteArray$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,com.ionspin.kotlin.bignum.integer.BigInteger)
    531:1593:public final java.lang.String byteArrayToInteger_S$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    536:1601:public final com.ionspin.kotlin.bignum.integer.BigInteger byteArrayToInteger_I$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    549:1648:public final void decimalToByteArray$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,java.lang.String)
    554:1697:public final void decimalToByteArray$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,com.ionspin.kotlin.bignum.decimal.BigDecimal)
    569:1708:public final com.ionspin.kotlin.bignum.decimal.BigDecimal byteArrayToDecimal_I$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    583:1727:public final java.lang.String byteArrayToDecimal_S$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    592:1756:public final void doubleToByteArray$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,double)
    599:1788:public final void doubleToByteArray$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,java.lang.String)
    604:1792:public final double byteArrayToDouble_I$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    609:1797:public final java.lang.String byteArrayToDouble_S$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    614:1826:public final void floatToByteArray$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,double)
    621:1858:public final void floatToByteArray$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,java.lang.String)
    626:1862:public final double byteArrayToFloat_I$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    631:1867:public final java.lang.String byteArrayToFloat_S$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    636:1898:public final void langToByteArray$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,java.lang.String,java.lang.String)
    649:1905:public final java.lang.String byteArrayToLang_Content$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    658:1910:public final java.lang.String byteArrayToLang_Lang$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    666:2356:public final void typedToByteArray$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,java.lang.String,java.lang.String)
    692:2363:public final java.lang.String byteArrayToTyped_Content$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    701:2368:public final java.lang.String byteArrayToTyped_Type$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    709:2393:public final void bnodeToByteArray$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,java.lang.String)
    718:2422:public final void bnodeToByteArray$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,long)
    725:2430:public final long byteArrayToBnode_I$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    734:2439:public final java.lang.String byteArrayToBnode_S$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    746:2452:public final java.lang.String byteArrayToBnode_A$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    758:2470:public final void iriToByteArray$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,java.lang.String)
    766:2474:public final java.lang.String byteArrayToIri$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    774:2478:public final java.lang.String byteArrayToString$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    782:2496:public final void stringToByteArray$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,java.lang.String)
    790:3312:public final void sparqlToByteArray$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,java.lang.String)
    856:871:public final java.lang.String removeQuotesFromString$luposdate3000_launch_endpoint(java.lang.String)
    876:4024:public final void valueDefinitionToByteArray$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,lupos.shared.ValueDefinition)
    881:4032:public final int byteArrayToType$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    889:4256:public final lupos.shared.XMLElement byteArrayToXMLElement$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    910:4486:public final java.lang.String byteArrayToSparql$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    937:4859:public final lupos.shared.ValueDefinition byteArrayToValueDefinition$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    977:5081:public final void byteArrayToCallback$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,kotlin.jvm.functions.Function1,kotlin.jvm.functions.Function1,kotlin.jvm.functions.Function2,kotlin.jvm.functions.Function1,kotlin.jvm.functions.Function2,kotlin.jvm.functions.Function1,kotlin.jvm.functions.Function1,kotlin.jvm.functions.Function1,kotlin.jvm.functions.Function1,kotlin.jvm.functions.Function1,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0)
    998:5191:public final int byteArrayCompareAny$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,lupos.shared.dynamicArray.ByteArrayWrapper)
lupos.shared.inline.DictionaryValueHelperInt
lupos.shared.inline.DictionaryValueHelperLong:
    144:144:public final long[] DictionaryValueTypeArrayOf$luposdate3000_launch_endpoint()
    147:147:public final long[] DictionaryValueTypeArrayOf$luposdate3000_launch_endpoint(long)
    150:150:public final long[] DictionaryValueTypeArrayOf$luposdate3000_launch_endpoint(long,long)
    153:153:public final long[] DictionaryValueTypeArrayOf$luposdate3000_launch_endpoint(long,long,long)
    156:244:public final void toByteArray$luposdate3000_launch_endpoint(byte[],int,long)
    159:254:public final void toByteArray$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,int,long)
    162:312:public final void toByteArrayX$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,int,long,int)
    165:369:public final void toByteArrayX$luposdate3000_launch_endpoint(byte[],int,long,int)
    168:370:public final long fromByteArray$luposdate3000_launch_endpoint(byte[],int)
    171:372:public final long fromByteArray$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,int)
    174:394:public final long fromByteArrayX$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,int,int)
    177:415:public final long fromByteArrayX$luposdate3000_launch_endpoint(byte[],int,int)
    180:180:public final int getSize$luposdate3000_launch_endpoint()
    183:183:public final int toInt$luposdate3000_launch_endpoint(long)
    186:186:public final long fromInt$luposdate3000_launch_endpoint(int)
    189:189:public final long fromString$luposdate3000_launch_endpoint(java.lang.String)
    193:417:public final int numberOfBytesUsed$luposdate3000_launch_endpoint(long)
    230:230:public final void sendToStream$luposdate3000_launch_endpoint(lupos.shared.IMyOutputStream,long)
    233:233:public final long readFromStream$luposdate3000_launch_endpoint(lupos.shared.IMyInputStream)
lupos.shared.inline.File:
    37:37:public final java.lang.String getAbsolutePath$luposdate3000_launch_endpoint()
    40:40:public final boolean exists$luposdate3000_launch_endpoint()
    43:43:public final boolean mkdirs$luposdate3000_launch_endpoint()
    46:46:public final boolean deleteRecursively$luposdate3000_launch_endpoint()
    49:49:public final long length$luposdate3000_launch_endpoint()
    52:52:public final java.lang.String readAsString$luposdate3000_launch_endpoint()
    55:55:public final kotlin.collections.CharIterator readAsCharIterator$luposdate3000_launch_endpoint()
    58:58:public final lupos.shared.IMyInputStream openInputStream$luposdate3000_launch_endpoint()
    61:61:public final lupos.shared.IMyOutputStream openOutputStream$luposdate3000_launch_endpoint(boolean)
    63:69:public final void walk$luposdate3000_launch_endpoint(int,kotlin.jvm.functions.Function1)
    72:75:public final void walk$luposdate3000_launch_endpoint(kotlin.jvm.functions.Function1)
    78:84:public final void withOutputStream$luposdate3000_launch_endpoint(kotlin.jvm.functions.Function1)
    87:93:public final void withInputStream$luposdate3000_launch_endpoint(kotlin.jvm.functions.Function1)
    95:97:public final void forEachLine$luposdate3000_launch_endpoint(kotlin.jvm.functions.Function1)
lupos.shared.inline.File$forEachLine$1
lupos.shared.inline.File$walk$1
lupos.shared.inline.File$walk$2
lupos.shared.inline.FileExt:
    24:25:public final java.lang.String createTempDirectory$luposdate3000_launch_endpoint()
lupos.shared.inline.MyInputStream:
    57:67:public int read(byte[],int,int)
    79:132:public long readLong()
lupos.shared.inline.MyInputStreamFixedLength
lupos.shared.inline.MyOutputStream:
    99:108:public final void _write$luposdate3000_launch_endpoint(byte[],int,int)
    118:174:public final void _print$luposdate3000_launch_endpoint(java.lang.String)
    125:226:public void print(int)
lupos.shared.inline.MyPrintWriter:
    22:45:public synthetic MyPrintWriter(boolean,int,kotlin.jvm.internal.DefaultConstructorMarker)
    76:79:public final void println(boolean)
    88:91:public final void println(int)
    94:97:public void print(int)
    100:103:public final void println(double)
lupos.shared.inline.MyStringExt:
    22:75:public final java.lang.String replaceEscapes$luposdate3000_launch_endpoint(java.lang.String,boolean)
lupos.shared.inline.MyStringStream:
    60:68:public int read(byte[],int,int)
    80:114:public long readLong()
    91:106:public java.lang.String readLine()
lupos.shared.inline.MyThreadReadWriteLock:
    34:34:public final long getUUID$luposdate3000_launch_endpoint()
    38:40:public final void downgradeToReadLock$luposdate3000_launch_endpoint()
    44:45:public final void readLock$luposdate3000_launch_endpoint()
    49:50:public final void readUnlock$luposdate3000_launch_endpoint()
    54:55:public final void writeLock$luposdate3000_launch_endpoint()
    59:59:public final boolean tryWriteLock$luposdate3000_launch_endpoint()
    64:65:public final void writeUnlock$luposdate3000_launch_endpoint()
    68:91:public final java.lang.Object withReadLock$luposdate3000_launch_endpoint(kotlin.jvm.functions.Function0)
    78:95:public final java.lang.Object withWriteLock$luposdate3000_launch_endpoint(kotlin.jvm.functions.Function0)
lupos.shared.inline.ParallelThread:
    23:23:public final java.lang.Object runBlocking$luposdate3000_launch_endpoint(kotlin.jvm.functions.Function0)
    27:31:public final lupos.shared.ParallelThreadJob launch$luposdate3000_launch_endpoint(kotlin.jvm.functions.Function0)
    36:37:public final void delay$luposdate3000_launch_endpoint(long)
    41:41:public final lupos.shared.inline.ParallelThreadCondition createCondition$luposdate3000_launch_endpoint()
    46:46:public final lupos.shared.inline.ParallelThreadQueue createQueue$luposdate3000_launch_endpoint(java.lang.Object)
lupos.shared.inline.ParallelThread$launch$res$1
lupos.shared.inline.ParallelThreadCondition:
    27:36:public final void waitCondition$luposdate3000_launch_endpoint(kotlin.jvm.functions.Function0)
    40:44:public final void signal$luposdate3000_launch_endpoint()
lupos.shared.inline.ParallelThreadQueue
lupos.shared.inline.Platform:
    40:40:public final java.lang.String getHostName$luposdate3000_launch_endpoint()
    44:44:public final int getOperatingSystem$luposdate3000_launch_endpoint()
    47:47:public final java.lang.String getUserHome$luposdate3000_launch_endpoint()
    50:50:public final java.lang.String getPathSeparator$luposdate3000_launch_endpoint()
    54:62:public final java.util.List findNamedFileInDirectory$luposdate3000_launch_endpoint(java.lang.String,java.lang.String)
    66:66:public final java.lang.String getNullFileName$luposdate3000_launch_endpoint()
    70:70:public final java.lang.String getEnv$luposdate3000_launch_endpoint(java.lang.String,java.lang.String)
    27:70:public static synthetic java.lang.String getEnv$luposdate3000_launch_endpoint$default(lupos.shared.inline.Platform,java.lang.String,java.lang.String,int,java.lang.Object)
    75:110:public final java.lang.String getGradleCache$luposdate3000_launch_endpoint()
    80:115:public final java.lang.String getMavenCache$luposdate3000_launch_endpoint()
    85:116:public final int getAvailableRam$luposdate3000_launch_endpoint()
    100:103:public final void setShutdownHock$luposdate3000_launch_endpoint(kotlin.jvm.functions.Function0)
lupos.shared.inline.Platform$setShutdownHock$1
lupos.shared.inline.SanityCheckOff:
    private static final boolean enabled
    25:25:public final boolean getEnabled()
    27:28:public final void println_buffermanager$luposdate3000_launch_endpoint(kotlin.jvm.functions.Function0)
    31:32:public final void println_nodemanager$luposdate3000_launch_endpoint(kotlin.jvm.functions.Function0)
    35:36:public final void println$luposdate3000_launch_endpoint(kotlin.jvm.functions.Function0)
    39:39:public final void invoke$luposdate3000_launch_endpoint(kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0)
    42:43:public final void suspended$luposdate3000_launch_endpoint(kotlin.jvm.functions.Function0)
    46:47:public final java.lang.Object helper$luposdate3000_launch_endpoint(kotlin.jvm.functions.Function0)
    51:52:public final void check$luposdate3000_launch_endpoint(kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0)
    55:56:public final void check$luposdate3000_launch_endpoint(kotlin.jvm.functions.Function0,kotlin.jvm.functions.Function0)
    59:59:public final java.lang.Void checkUnreachable$luposdate3000_launch_endpoint()
lupos.shared.inline.SanityCheckOn
lupos.shared.inline.dynamicArray.ByteArrayWrapperExt:
    24:24:public final int getSize(lupos.shared.dynamicArray.ByteArrayWrapper)
    29:29:public final byte[] getBuf(lupos.shared.dynamicArray.ByteArrayWrapper)
    34:38:public final void setSize(lupos.shared.dynamicArray.ByteArrayWrapper,int)
    42:48:public final void setSizeCopy(lupos.shared.dynamicArray.ByteArrayWrapper,int)
    52:60:public final int commonBytes(lupos.shared.dynamicArray.ByteArrayWrapper,lupos.shared.dynamicArray.ByteArrayWrapper)
    65:88:public final void copyInto(lupos.shared.dynamicArray.ByteArrayWrapper,lupos.shared.dynamicArray.ByteArrayWrapper)
    71:93:public final void appendTo(lupos.shared.dynamicArray.ByteArrayWrapper,lupos.shared.dynamicArray.ByteArrayWrapper)
    78:98:public final void appendTo(byte[],lupos.shared.dynamicArray.ByteArrayWrapper)
lupos.shared.inline.dynamicArray.IntArrayWrapperExt
lupos.shared.inline.fileformat.DictionaryIntermediate:
    public static final java.lang.String filenameEnding
    31:61:public final lupos.shared.inline.File getFile$luposdate3000_launch_endpoint()
lupos.shared.inline.fileformat.DictionaryIntermediate$Companion:
    38:38:public final lupos.shared.inline.File getFile$luposdate3000_launch_endpoint(java.lang.String)
    42:62:public final boolean fileExists$luposdate3000_launch_endpoint(java.lang.String)
    56:65:public final void delete$luposdate3000_launch_endpoint(java.lang.String)
lupos.shared.inline.fileformat.DictionaryIntermediateReader:
    34:91:public final void readAll$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,kotlin.jvm.functions.Function1)
    41:41:public final boolean hasNext$luposdate3000_launch_endpoint()
    45:100:public final void next$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper,kotlin.jvm.functions.Function1)
    57:118:public final lupos.shared.fileformat.DictionaryIntermediateRow next$luposdate3000_launch_endpoint(lupos.shared.dynamicArray.ByteArrayWrapper)
    65:67:public final void close$luposdate3000_launch_endpoint()
lupos.shared.inline.fileformat.DictionaryIntermediateWriter:
    33:76:public final void writeAssumeOrdered$luposdate3000_launch_endpoint(lupos.shared.fileformat.DictionaryIntermediateRow)
    38:79:public final void writeAssumeOrdered$luposdate3000_launch_endpoint(long,lupos.shared.dynamicArray.ByteArrayWrapper)
    45:106:public final void write$luposdate3000_launch_endpoint(java.util.Map)
    56:107:public final void close$luposdate3000_launch_endpoint()
lupos.shared.inline.fileformat.TriplesIntermediate:
    public static final int version
    public static final java.lang.String filenameEnding
lupos.shared.inline.fileformat.TriplesIntermediate$Companion:
    36:41:public final void delete$luposdate3000_launch_endpoint(java.lang.String)
lupos.shared.inline.fileformat.TriplesIntermediateReader:
    44:246:public final void readAll$luposdate3000_launch_endpoint(kotlin.jvm.functions.Function1)
    58:315:public final long[] next$luposdate3000_launch_endpoint()
    81:83:public final void close$luposdate3000_launch_endpoint()
lupos.shared.inline.fileformat.TriplesIntermediateWriter:
    52:52:public final long getCount$luposdate3000_launch_endpoint()
    57:393:public final void write$luposdate3000_launch_endpoint(long,long,long)
    114:118:public final void close$luposdate3000_launch_endpoint()
lupos.shared.operator.IOPBase:
    public abstract int getPartitionCount(java.lang.String)
    public abstract void setSortPriorities(java.util.List)
    public abstract boolean getSortPrioritiesInitialized()
    public abstract lupos.shared.operator.IOPBase getParent()
lupos.shared.operator.IOPBase$DefaultImpls
lupos.shared.operator.iterator.IteratorBundle:
    37:37:public final boolean hasCountMode()
lupos.shared.operator.iterator.IteratorBundleModeExt
lupos.shared.operator.iterator.IteratorBundleModeKt
lupos.shared.xmlParser.ParserContext:
    public static final int EOF
    57:58:public final void clear$luposdate3000_shared()
    62:62:public final java.lang.String getValue$luposdate3000_shared()
lupos.shared.xmlParser.XMLParserGeneratedKt
lupos.test_buffermanager.ExecuteBufferManagerTestKt
lupos.test_buffermanager.ExecuteBufferManagerTestKt$executeBufferManagerTest$1
lupos.test_buffermanager.ExecuteBufferManagerTestKt$executeBufferManagerTest$2
lupos.test_buffermanager.ExecuteBufferManagerTestKt$executeBufferManagerTest$3
lupos.test_buffermanager.ExecuteBufferManagerTestKt$executeBufferManagerTest$4
lupos.test_buffermanager.ExecuteBufferManagerTestKt$executeBufferManagerTest$5
lupos.test_buffermanager.ExecuteBufferManagerTestKt$executeBufferManagerTest$6
lupos.test_buffermanager.ExecuteBufferManagerTestKt$executeBufferManagerTest$7
lupos.test_buffermanager.ExecuteBufferManagerTestKt$executeBufferManagerTest$8
lupos.test_buffermanager.ExecuteBufferManagerTestKt$executeBufferManagerTest$9
lupos.triple_store_id_triple.TripleStoreIndexIDTriple:
    147:813:public final void setFirstLeaf$luposdate3000_triple_store_id_triple(int)
    156:823:public final void setRoot$luposdate3000_triple_store_id_triple(int)
    165:833:public final void setCountPrimary$luposdate3000_triple_store_id_triple(int)
    174:843:public final void setDistinctPrimary$luposdate3000_triple_store_id_triple(int)
    183:187:private final void clearCachedHistogram()
    392:2402:private final int importHelper(int,int,kotlin.jvm.functions.Function2)
    442:3077:private final void flushContinueWithWriteLock()
    448:3087:private final void flushContinueWithReadLock()
    468:3149:private final int collapseList(java.util.List)
lupos.triple_store_id_triple.index_IDTriple.DebugPassThroughIterator
lupos.triple_store_id_triple.index_IDTriple.EmptyIterator
lupos.triple_store_id_triple.index_IDTriple.NodeInner:
    public static final int START_OFFSET
    public static final int MAX_POINTER_SIZE
    32:472:public final void getFirstTriple$luposdate3000_triple_store_id_triple(byte[],long[],lupos.triple_store_id_triple.index_IDTriple.NodeManager)
    56:482:public final void setFirstChild$luposdate3000_triple_store_id_triple(byte[],int)
    61:487:public final int getFirstChild$luposdate3000_triple_store_id_triple(byte[])
    66:497:public final int writeChildPointer$luposdate3000_triple_store_id_triple(byte[],int,int)
    71:502:public final int readChildPointer$luposdate3000_triple_store_id_triple(byte[],int,kotlin.jvm.functions.Function1)
    77:532:public final lupos.triple_store_id_triple.index_IDTriple.TripleIterator iterator$luposdate3000_triple_store_id_triple(byte[],lupos.triple_store_id_triple.index_IDTriple.NodeManager)
    102:574:public final lupos.shared.operator.iterator.ColumnIterator iterator$luposdate3000_triple_store_id_triple(byte[],lupos.shared.inline.MyThreadReadWriteLock,int,lupos.triple_store_id_triple.index_IDTriple.NodeManager)
    126:606:public final void forEachChild$luposdate3000_triple_store_id_triple(byte[],kotlin.jvm.functions.Function1)
    141:708:public final void findIteratorN$luposdate3000_triple_store_id_triple(byte[],kotlin.jvm.functions.Function3,kotlin.jvm.functions.Function1)
    166:845:public final lupos.shared.operator.iterator.ColumnIterator iterator3$luposdate3000_triple_store_id_triple(byte[],long[],lupos.shared.inline.MyThreadReadWriteLock,lupos.triple_store_id_triple.index_IDTriple.NodeManager)
    200:982:public final lupos.shared.operator.iterator.ColumnIterator iterator2$luposdate3000_triple_store_id_triple(byte[],long[],lupos.shared.inline.MyThreadReadWriteLock,lupos.triple_store_id_triple.index_IDTriple.NodeManager)
    234:1128:public final lupos.shared.operator.iterator.ColumnIterator iterator1$luposdate3000_triple_store_id_triple(byte[],long[],lupos.shared.inline.MyThreadReadWriteLock,int,lupos.triple_store_id_triple.index_IDTriple.NodeManager)
    268:1663:public final void initializeWith$luposdate3000_triple_store_id_triple(byte[],java.util.List,lupos.triple_store_id_triple.index_IDTriple.NodeManager)
lupos.triple_store_id_triple.index_IDTriple.NodeLeaf:
    public static final int START_OFFSET
    30:222:public final void getFirstTriple$luposdate3000_triple_store_id_triple(byte[],long[])
    39:39:public final lupos.triple_store_id_triple.index_IDTriple.TripleIterator iterator$luposdate3000_triple_store_id_triple(byte[],int,lupos.triple_store_id_triple.index_IDTriple.NodeManager)
    44:223:public final lupos.shared.operator.iterator.ColumnIterator iterator$luposdate3000_triple_store_id_triple(byte[],int,lupos.shared.inline.MyThreadReadWriteLock,int,lupos.triple_store_id_triple.index_IDTriple.NodeManager)
    62:62:public final lupos.shared.operator.iterator.ColumnIterator iterator3$luposdate3000_triple_store_id_triple(byte[],int,long[],lupos.shared.inline.MyThreadReadWriteLock,lupos.triple_store_id_triple.index_IDTriple.NodeManager)
    67:67:public final lupos.shared.operator.iterator.ColumnIterator iterator2$luposdate3000_triple_store_id_triple(byte[],int,long[],lupos.shared.inline.MyThreadReadWriteLock,lupos.triple_store_id_triple.index_IDTriple.NodeManager)
    72:224:public final lupos.shared.operator.iterator.ColumnIterator iterator1$luposdate3000_triple_store_id_triple(byte[],int,long[],lupos.shared.inline.MyThreadReadWriteLock,int,lupos.triple_store_id_triple.index_IDTriple.NodeManager)
    87:497:public final void initializeWith$luposdate3000_triple_store_id_triple(byte[],lupos.triple_store_id_triple.index_IDTriple.TripleIterator)
lupos.triple_store_id_triple.index_IDTriple.NodeLeafColumnIterator:
    39:95:public final void __init$luposdate3000_triple_store_id_triple()
    45:107:public final void _close$luposdate3000_triple_store_id_triple()
    65:176:public final void updateRemaining$luposdate3000_triple_store_id_triple(kotlin.jvm.functions.Function0)
    64:225:public static synthetic void updateRemaining$luposdate3000_triple_store_id_triple$default(lupos.triple_store_id_triple.index_IDTriple.NodeLeafColumnIterator,kotlin.jvm.functions.Function0,int,java.lang.Object)
lupos.triple_store_id_triple.index_IDTriple.NodeLeafColumnIterator$updateRemaining$1
lupos.triple_store_id_triple.index_IDTriple.NodeLeafIterator:
    52:217:private final void updateRemaining()
lupos.triple_store_id_triple.index_IDTriple.NodeManager:
    public static final int nodeTypeLeaf
    public static final int nodeTypeInner
    public static final int nodeNullPointer
    35:188:public final void releaseNode$luposdate3000_triple_store_id_triple(java.lang.String,int)
    41:190:public final void flushNode$luposdate3000_triple_store_id_triple(java.lang.String,int)
    46:192:public final void getNodeLeaf$luposdate3000_triple_store_id_triple(java.lang.String,int,kotlin.jvm.functions.Function1)
    52:201:public final void getNodeAny$luposdate3000_triple_store_id_triple(java.lang.String,int,kotlin.jvm.functions.Function1,kotlin.jvm.functions.Function1)
    68:210:public final void getNodeAnySuspended$luposdate3000_triple_store_id_triple(java.lang.String,int,kotlin.jvm.functions.Function1,kotlin.jvm.functions.Function1)
    84:248:public final void allocateNodeLeaf$luposdate3000_triple_store_id_triple(java.lang.String,kotlin.jvm.functions.Function2)
    94:286:public final void allocateNodeInner$luposdate3000_triple_store_id_triple(java.lang.String,kotlin.jvm.functions.Function2)
    106:288:public final void freeNode$luposdate3000_triple_store_id_triple(java.lang.String,int)
    113:295:public final void freeNodeAndAllRelated$luposdate3000_triple_store_id_triple(java.lang.String,int)
    143:367:public final void freeAllInner$luposdate3000_triple_store_id_triple(java.lang.String,int)
    174:456:public final void freeAllLeaves$luposdate3000_triple_store_id_triple(java.lang.String,int)
lupos.triple_store_id_triple.index_IDTriple.NodeShared:
    public static final int MAX_TRIPLE_SIZE
    29:240:public final void setNodeType$luposdate3000_triple_store_id_triple(byte[],int)
    34:245:public final int getNodeType$luposdate3000_triple_store_id_triple(byte[])
    39:255:public final void setNextNode$luposdate3000_triple_store_id_triple(byte[],int)
    44:260:public final int getNextNode$luposdate3000_triple_store_id_triple(byte[])
    49:270:public final void setTripleCount$luposdate3000_triple_store_id_triple(byte[],int)
    54:275:public final int getTripleCount$luposdate3000_triple_store_id_triple(byte[])
    58:59:public final void decodeTripleHeader$luposdate3000_triple_store_id_triple(int,kotlin.jvm.functions.Function3)
    62:276:private final void encodeTripleHeader(int,int,int,kotlin.jvm.functions.Function1)
    78:283:public final int readTriple000$luposdate3000_triple_store_id_triple(byte[],int)
    87:356:public final int readTriple111$luposdate3000_triple_store_id_triple(byte[],int,long,long,long,kotlin.jvm.functions.Function3)
    102:385:public final int readTriple010$luposdate3000_triple_store_id_triple(byte[],int,long,kotlin.jvm.functions.Function1)
    114:414:public final int readTriple001$luposdate3000_triple_store_id_triple(byte[],int,long,kotlin.jvm.functions.Function1)
    126:443:public final int readTriple100$luposdate3000_triple_store_id_triple(byte[],int,long,kotlin.jvm.functions.Function1)
    137:494:public final int readTriple110$luposdate3000_triple_store_id_triple(byte[],int,long,long,kotlin.jvm.functions.Function2)
    150:545:public final int readTriple101$luposdate3000_triple_store_id_triple(byte[],int,long,long,kotlin.jvm.functions.Function2)
    164:846:public final int writeTriple$luposdate3000_triple_store_id_triple(byte[],int,long[],long[])
lupos.triple_store_manager.ETripleStoreIndexDescriptionPartitionedTypeExt
lupos.triple_store_manager.ETripleStoreIndexDescriptionPartitionedTypeKt
lupos.triple_store_manager.POPTripleStoreIterator:
    78:78:public final int getIndexPattern()
    102:165:public int getPartitionCount(java.lang.String)
lupos.triple_store_manager.TripleStoreDescription:
    109:113:public final java.util.List getAllLocations$luposdate3000_triple_store_manager()
lupos.triple_store_manager.TripleStoreDescriptionModifyCache$LocalInputStream:
    88:88:public final java.lang.String getKey()
    88:88:public final int getMode()
    88:88:public final int getIdx()
    88:88:public final lupos.shared.Luposdate3000Instance getInstance()
    89:89:public final int getOff()
    89:89:public final void setOff(int)
    90:90:public final long[] getBuf()
    91:91:public final int getLimit()
    92:92:public final lupos.shared.TripleStoreIndex getStore()
    103:103:public void print(int)
lupos.triple_store_manager.TripleStoreDescriptionModifyCache$LocalSortedInputStream:
    34:34:public final java.lang.String getKey()
    34:34:public final int getMode()
    34:34:public final int getIdx()
    34:34:public final lupos.shared.Luposdate3000Instance getInstance()
    35:35:public final int getOff()
    35:35:public final void setOff(int)
    36:36:public final long[] getBuf()
    37:37:public final int getLimit()
    38:38:public final lupos.shared.TripleStoreIndex getStore()
    49:49:public void print(int)
lupos.triple_store_manager.TripleStoreIndexDescriptionPartitionedByKey:
    43:43:public final int getKey_size$luposdate3000_triple_store_manager()
lupos.triple_store_manager.TripleStoreManagerImpl:
    80:80:public final java.util.Map localStoresGet$luposdate3000_triple_store_manager()
    84:84:public final java.util.Map metadataGet$luposdate3000_triple_store_manager()
    88:737:private final byte[] toByteArray()
    137:744:private final void initFromByteArray(byte[])
    178:179:private final void keysOnHostnameAdd(int,java.lang.String)
    183:746:private final void localStoresAdd(java.lang.String,lupos.shared.TripleStoreIndex)
    189:192:private final void localStoresRemove(java.lang.String)
    196:748:private final void metadataAdd(java.lang.String,lupos.triple_store_manager.TripleStoreDescription)
    202:750:private final void metadataRemove(java.lang.String)
    236:819:private final void initFromPageID()
    260:834:private final void deleteAllPagesExceptRootID()
    280:914:private final void writeToPageID()
    322:1192:public void delete()
    412:1208:public final kotlin.Pair getNextHostAndKey$luposdate3000_triple_store_manager()
    502:1238:private final void createGraphShared(lupos.triple_store_manager.TripleStoreDescription)
    543:545:public void resetGraph(lupos.shared.IQuery,java.lang.String)
lupos.triple_store_manager.TripleStoreManagerImpl$resetDefaultTripleStoreLayout$19
lupos.triple_store_manager.TripleStoreManagerImpl$resetDefaultTripleStoreLayout$20
lupos.triple_store_manager.TripleStoreManagerImpl$resetDefaultTripleStoreLayout$21
lupos.triple_store_manager.TripleStoreManagerImpl$resetDefaultTripleStoreLayout$22
lupos.triple_store_manager.TripleStoreManagerImpl$resetDefaultTripleStoreLayout$23
lupos.triple_store_manager.TripleStoreManagerImpl$resetDefaultTripleStoreLayout$24
lupos.triple_store_manager.TripleStoreManagerImpl$resetDefaultTripleStoreLayout$25
lupos.triple_store_manager.TripleStoreManagerImpl$resetGraph$1
lupos.vk.ValueKeyStore:
    public static final int ID_NULL
    public static final int PAGEID_NULL_PTR
    public static final int PAGE_TYPE_LEAF
    public static final int PAGE_TYPE_INNER
    public static final int RESERVED_SPACE
    public static final int MIN_BRANCHING
    74:77:public final void delete()
    107:107:public final lupos.vk.ValueKeyStoreIteratorLeaf getIterator(lupos.shared.dynamicArray.ByteArrayWrapper)
lupos.vk.ValueKeyStoreIteratorSearch:
    419:539:public final int search$luposdate3000_vk(lupos.shared.dynamicArray.ByteArrayWrapper,int,lupos.shared.IBufferManager,lupos.shared.dynamicArray.ByteArrayWrapper)
lupos.vk.ValueKeyStoreWriter:
    289:290:public final void write$luposdate3000_vk(int,lupos.shared.dynamicArray.ByteArrayWrapper)
    303:692:public final void write$luposdate3000_vk(int,int,lupos.shared.dynamicArray.ByteArrayWrapper,kotlin.jvm.functions.Function0)
