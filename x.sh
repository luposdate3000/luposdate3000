#!/bin/bash
find src -name *.kt | xargs sed "s/ApplicationLayerLogger/ApplicationStack_Logger/g" -i
find src -name *.kt | xargs sed "s/ApplicationLayerMergeMessages/ApplicationStack_MergeMessages/g" -i
find src -name *.kt | xargs sed "s/ApplicationLayerMergeMessages_Package/Package_ApplicationStack_MergeMessages/g" -i
find src -name *.kt | xargs sed "s/ApplicationLayerMultipleChilds/ApplicationStack_MultipleChilds/g" -i
find src -name *.kt | xargs sed "s/ApplicationLayerSequence/ApplicationStack_Sequence/g" -i
find src -name *.kt | xargs sed "s/ApplicationLayerSequence_Package/Package_ApplicationStack_Sequence/g" -i
find src -name *.kt | xargs sed "s/IDatabasePackage/IPackage_Database/g" -i
find src -name *.kt | xargs sed "s/IDatabasePackageTesting/IPackage_DatabaseTesting/g" -i
find src -name *.kt | xargs sed "s/IUserApplicationBoth/IApplicationStack_BothDirections/g" -i
find src -name *.kt | xargs sed "s/IUserApplicationLayer/IApplicationStack_Middleware/g" -i
find src -name *.kt | xargs sed "s/IUserApplication/IApplicationStack_Actuator/g" -i
find src -name *.kt | xargs sed "s/QueryPackage/Package_Query/g" -i
find src -name *.kt | xargs sed "s/QueryResponsePackage/Package_QueryResponse/g" -i


exit
find src -name *.kt | xargs sed "s///g" -i
find src -name *.kt | xargs sed "s///g" -i
find src -name *.kt | xargs sed "s///g" -i


to remove
DatabaseState
IDatabase


to moved
IPayloadLayer
