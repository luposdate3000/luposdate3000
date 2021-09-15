#!/bin/bash
find . -name "*.kt" | xargs sed "s/DummyApplication_DatabaseDummy_State/Application_DatabaseDummy_State/g" -i
find . -name "*.kt" | xargs sed "s/OperatorGraph/Application_DatabaseDummy_OperatorGraph/g" -i
find . -name "*.kt" | xargs sed "s/OperatorGraphPart/Application_DatabaseDummy_OperatorGraphPart/g" -i
find . -name "*.kt" | xargs sed "s/Optimizer/Application_DatabaseDummy_Optimizer/g" -i
find . -name "*.kt" | xargs sed "s/PreprocessingPackage/Package_DatabaseDummy_Preprocessing/g" -i
find . -name "*.kt" | xargs sed "s/ChoosenOperatorPackage/Package_DatabaseDummy_ChoosenOperator/g" -i
find . -name "*.kt" | xargs sed "s/ResultPackage/Package_DatabaseDummy_Result/g" -i
find . -name "*.kt" | xargs sed "s/Query/Application_DatabaseDummy_Query/g" -i
find . -name "*.kt" | xargs sed "s/ReceivedResults/Application_DatabaseDummy_ReceivedResults/g" -i
