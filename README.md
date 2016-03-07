DQS Java Library
================

This code implements a java interface to the Dacura Quality Service.

It produces a command line programme that can also be called as a library 
by simply supplying the appropriate arguments.  

The signure of the arguments is: 

FileName test1 test2 ... testn 

Where each of the arguments is a string.  

If test1 is 'all' and no further tests are provided, then all tests will be run. 

The following are recognised tests (SC == Schema Constraint): 

classCycleSC
propertyCycleSC
noImmediateClassSC
noImmediateDomainSC
noImmediateRangeSC
notUniqueClassLabelSC
notUniqueClassSC
notUniquePropertySC
schemaBlankNodeSC
annotationOverloadSC
orphanClassSC
orphanPropertySC 
invalidRangeSC 
invalidDomainSC
domainNotSubsumedSC
rangeNotSubsumedSC
propertyTypeOverloadSC
