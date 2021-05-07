(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'Luposdate3000_Optimizer_Logical', 'Luposdate3000_Operator_Physical', 'Luposdate3000_Operator_Base', 'Luposdate3000_Operator_Logical', 'Luposdate3000_Shared', 'Luposdate3000_Triple_Store_Manager', 'Luposdate3000_Operator_Arithmetik', 'KotlinBigInteger-bignum-jsLegacy', 'Luposdate3000_Shared_JS'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('Luposdate3000_Optimizer_Logical'), require('Luposdate3000_Operator_Physical'), require('Luposdate3000_Operator_Base'), require('Luposdate3000_Operator_Logical'), require('Luposdate3000_Shared'), require('Luposdate3000_Triple_Store_Manager'), require('Luposdate3000_Operator_Arithmetik'), require('KotlinBigInteger-bignum-jsLegacy'), require('Luposdate3000_Shared_JS'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Physical'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Luposdate3000_Optimizer_Physical'.");
    }if (typeof Luposdate3000_Optimizer_Logical === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Physical'. Its dependency 'Luposdate3000_Optimizer_Logical' was not found. Please, check whether 'Luposdate3000_Optimizer_Logical' is loaded prior to 'Luposdate3000_Optimizer_Physical'.");
    }if (typeof Luposdate3000_Operator_Physical === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Physical'. Its dependency 'Luposdate3000_Operator_Physical' was not found. Please, check whether 'Luposdate3000_Operator_Physical' is loaded prior to 'Luposdate3000_Optimizer_Physical'.");
    }if (typeof Luposdate3000_Operator_Base === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Physical'. Its dependency 'Luposdate3000_Operator_Base' was not found. Please, check whether 'Luposdate3000_Operator_Base' is loaded prior to 'Luposdate3000_Optimizer_Physical'.");
    }if (typeof Luposdate3000_Operator_Logical === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Physical'. Its dependency 'Luposdate3000_Operator_Logical' was not found. Please, check whether 'Luposdate3000_Operator_Logical' is loaded prior to 'Luposdate3000_Optimizer_Physical'.");
    }if (typeof Luposdate3000_Shared === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Physical'. Its dependency 'Luposdate3000_Shared' was not found. Please, check whether 'Luposdate3000_Shared' is loaded prior to 'Luposdate3000_Optimizer_Physical'.");
    }if (typeof Luposdate3000_Triple_Store_Manager === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Physical'. Its dependency 'Luposdate3000_Triple_Store_Manager' was not found. Please, check whether 'Luposdate3000_Triple_Store_Manager' is loaded prior to 'Luposdate3000_Optimizer_Physical'.");
    }if (typeof Luposdate3000_Operator_Arithmetik === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Physical'. Its dependency 'Luposdate3000_Operator_Arithmetik' was not found. Please, check whether 'Luposdate3000_Operator_Arithmetik' is loaded prior to 'Luposdate3000_Optimizer_Physical'.");
    }if (typeof this['KotlinBigInteger-bignum-jsLegacy'] === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Physical'. Its dependency 'KotlinBigInteger-bignum-jsLegacy' was not found. Please, check whether 'KotlinBigInteger-bignum-jsLegacy' is loaded prior to 'Luposdate3000_Optimizer_Physical'.");
    }if (typeof Luposdate3000_Shared_JS === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Optimizer_Physical'. Its dependency 'Luposdate3000_Shared_JS' was not found. Please, check whether 'Luposdate3000_Shared_JS' is loaded prior to 'Luposdate3000_Optimizer_Physical'.");
    }root.Luposdate3000_Optimizer_Physical = factory(typeof Luposdate3000_Optimizer_Physical === 'undefined' ? {} : Luposdate3000_Optimizer_Physical, kotlin, Luposdate3000_Optimizer_Logical, Luposdate3000_Operator_Physical, Luposdate3000_Operator_Base, Luposdate3000_Operator_Logical, Luposdate3000_Shared, Luposdate3000_Triple_Store_Manager, Luposdate3000_Operator_Arithmetik, this['KotlinBigInteger-bignum-jsLegacy'], Luposdate3000_Shared_JS);
  }
}(this, function (_, Kotlin, $module$Luposdate3000_Optimizer_Logical, $module$Luposdate3000_Operator_Physical, $module$Luposdate3000_Operator_Base, $module$Luposdate3000_Operator_Logical, $module$Luposdate3000_Shared, $module$Luposdate3000_Triple_Store_Manager, $module$Luposdate3000_Operator_Arithmetik, $module$KotlinBigInteger_bignum_jsLegacy, $module$Luposdate3000_Shared_JS) {
  'use strict';
  var OptimizerCompoundBase = $module$Luposdate3000_Optimizer_Logical.lupos.optimizer.logical.OptimizerCompoundBase;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var POPBase = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.POPBase;
  var POPDebug = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.singleinput.POPDebug;
  var OPBaseCompound = $module$Luposdate3000_Operator_Base.lupos.operator.base.OPBaseCompound;
  var Unit = Kotlin.kotlin.Unit;
  var OptimizerBase = $module$Luposdate3000_Optimizer_Logical.lupos.optimizer.logical.OptimizerBase;
  var LOPProjection = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.LOPProjection;
  var POPProjection = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.singleinput.POPProjection;
  var shared = $module$Luposdate3000_Shared.lupos.shared;
  var POPSplitPartition = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPSplitPartition;
  var POPMergePartitionCount = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPMergePartitionCount;
  var POPMergePartitionOrderedByIntId = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPMergePartitionOrderedByIntId;
  var POPMergePartition = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPMergePartition;
  var LOPJoin = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.multiinput.LOPJoin;
  var multiinput = $module$Luposdate3000_Operator_Base.lupos.operator.base.multiinput;
  var POPJoinCartesianProduct = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.multiinput.POPJoinCartesianProduct;
  var equals = Kotlin.equals;
  var POPJoinMergeOptional = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.multiinput.POPJoinMergeOptional;
  var POPJoinMergeSingleColumn = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.multiinput.POPJoinMergeSingleColumn;
  var POPJoinMerge = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.multiinput.POPJoinMerge;
  var POPJoinHashMap = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.multiinput.POPJoinHashMap;
  var LOPTriple = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.noinput.LOPTriple;
  var POPJoinWithStoreExists = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.multiinput.POPJoinWithStoreExists;
  var POPJoinWithStore = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.multiinput.POPJoinWithStore;
  var POPTripleStoreIterator = $module$Luposdate3000_Triple_Store_Manager.lupos.triple_store_manager.POPTripleStoreIterator;
  var collectionSizeOrDefault = Kotlin.kotlin.collections.collectionSizeOrDefault_ba2ldo$;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  var POPSort = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.singleinput.POPSort;
  var LOPSortAny = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.modifiers.LOPSortAny;
  var POPGraphOperation = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.noinput.POPGraphOperation;
  var LOPGraphOperation = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.noinput.LOPGraphOperation;
  var POPModify = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.singleinput.POPModify;
  var LOPModify = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.LOPModify;
  var POPModifyData = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.noinput.POPModifyData;
  var LOPModifyData = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.noinput.LOPModifyData;
  var POPMakeBooleanResult = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.singleinput.POPMakeBooleanResult;
  var LOPMakeBooleanResult = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.LOPMakeBooleanResult;
  var POPValues_init = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.noinput.POPValues_init_twwug2$;
  var LOPValues = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.noinput.LOPValues;
  var POPLimit = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.singleinput.modifiers.POPLimit;
  var LOPLimit = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.modifiers.LOPLimit;
  var POPReduced = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.singleinput.modifiers.POPReduced;
  var LOPReduced = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.modifiers.LOPReduced;
  var POPOffset = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.singleinput.modifiers.POPOffset;
  var LOPOffset = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.modifiers.LOPOffset;
  var POPGroup_init = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.singleinput.POPGroup_init_99qn5e$;
  var LOPGroup = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.LOPGroup;
  var POPUnion = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.multiinput.POPUnion;
  var LOPUnion = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.multiinput.LOPUnion;
  var POPMinus = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.multiinput.POPMinus;
  var LOPMinus = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.multiinput.LOPMinus;
  var LOPSort = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.LOPSort;
  var AOPBase = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.AOPBase;
  var throwCCE = Kotlin.throwCCE;
  var POPFilter = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.singleinput.POPFilter;
  var LOPFilter = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.LOPFilter;
  var POPBind = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.singleinput.POPBind;
  var LOPBind = $module$Luposdate3000_Operator_Logical.lupos.operator.logical.singleinput.LOPBind;
  var IAOPBase = $module$Luposdate3000_Shared.lupos.shared.operator.IAOPBase;
  var POPEmptyRow = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.noinput.POPEmptyRow;
  var OPEmptyRow = $module$Luposdate3000_Operator_Base.lupos.operator.base.noinput.OPEmptyRow;
  var ArrayList_init_0 = Kotlin.kotlin.collections.ArrayList_init_287e2$;
  var copyToArray = Kotlin.kotlin.collections.copyToArray;
  var Array_0 = Array;
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var POPSplitPartitionFromStore = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPSplitPartitionFromStore;
  var POPSplitPartitionFromStoreCount = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPSplitPartitionFromStoreCount;
  var POPChangePartitionOrderedByIntId = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.partition.POPChangePartitionOrderedByIntId;
  var AOPVariable = $module$Luposdate3000_Operator_Arithmetik.lupos.operator.arithmetik.noinput.AOPVariable;
  var printStackTrace = Kotlin.kotlin.printStackTrace_dbl4o4$;
  var Throwable = Error;
  var Exception_init = Kotlin.kotlin.Exception_init_pdl1vj$;
  var POPVisualisation = $module$Luposdate3000_Operator_Physical.lupos.operator.physical.singleinput.POPVisualisation;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var arrayCopy = Kotlin.kotlin.collections.arrayCopy;
  var indexOf = Kotlin.kotlin.text.indexOf_8eortd$;
  var toInt = Kotlin.kotlin.text.toInt_pdl1vz$;
  var BigInteger = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.integer.BigInteger;
  var BigDecimal = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.decimal.BigDecimal;
  var toByte = Kotlin.toByte;
  var Sign = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.integer.Sign;
  var BigInteger_init = $module$KotlinBigInteger_bignum_jsLegacy.com.ionspin.kotlin.bignum.integer.BigInteger_init_za3lpa$;
  var split = Kotlin.kotlin.text.split_ip8yn$;
  var padStart = Kotlin.kotlin.text.padStart_vrc1nu$;
  var endsWith = Kotlin.kotlin.text.endsWith_sgbm27$;
  var toBoxedChar = Kotlin.toBoxedChar;
  var contains = Kotlin.kotlin.text.contains_sgbm27$;
  var toDouble = Kotlin.kotlin.text.toDouble_pdl1vz$;
  var encodeToByteArray = Kotlin.kotlin.text.encodeToByteArray_pdl1vz$;
  var decodeToString = Kotlin.kotlin.text.decodeToString_964n91$;
  var Exception = Kotlin.kotlin.Exception;
  var startsWith = Kotlin.kotlin.text.startsWith_7epoxm$;
  var endsWith_0 = Kotlin.kotlin.text.endsWith_7epoxm$;
  var contains_0 = Kotlin.kotlin.text.contains_li3zpu$;
  var lastIndexOf = Kotlin.kotlin.text.lastIndexOf_l5u8uk$;
  var dictionary = $module$Luposdate3000_Shared.lupos.shared.dictionary;
  var toString = Kotlin.toString;
  var ValueBnode = $module$Luposdate3000_Shared.lupos.shared.ValueBnode;
  var ValueDouble = $module$Luposdate3000_Shared.lupos.shared.ValueDouble;
  var ValueFloat = $module$Luposdate3000_Shared.lupos.shared.ValueFloat;
  var ValueInteger = $module$Luposdate3000_Shared.lupos.shared.ValueInteger;
  var ValueDecimal = $module$Luposdate3000_Shared.lupos.shared.ValueDecimal;
  var ValueIri = $module$Luposdate3000_Shared.lupos.shared.ValueIri;
  var ValueSimpleLiteral = $module$Luposdate3000_Shared.lupos.shared.ValueSimpleLiteral;
  var ValueLanguageTaggedLiteral = $module$Luposdate3000_Shared.lupos.shared.ValueLanguageTaggedLiteral;
  var ValueTypedLiteral = $module$Luposdate3000_Shared.lupos.shared.ValueTypedLiteral;
  var toByteArray = Kotlin.kotlin.collections.toByteArray_kdx1v$;
  var IMyInputStream = $module$Luposdate3000_Shared.lupos.shared.IMyInputStream;
  var UnreachableException = $module$Luposdate3000_Shared.lupos.shared.UnreachableException;
  var L255 = Kotlin.Long.fromInt(255);
  var toChar = Kotlin.toChar;
  var NotImplementedException = $module$Luposdate3000_Shared.lupos.shared.NotImplementedException;
  var StringBuilder_init = Kotlin.kotlin.text.StringBuilder_init;
  var fs = $module$Luposdate3000_Shared_JS.ext.fs;
  var IMyOutputStream = $module$Luposdate3000_Shared.lupos.shared.IMyOutputStream;
  var ensureNotNull = Kotlin.ensureNotNull;
  PhysicalOptimizer.prototype = Object.create(OptimizerCompoundBase.prototype);
  PhysicalOptimizer.prototype.constructor = PhysicalOptimizer;
  PhysicalOptimizerDebug.prototype = Object.create(OptimizerBase.prototype);
  PhysicalOptimizerDebug.prototype.constructor = PhysicalOptimizerDebug;
  PhysicalOptimizerJoinType.prototype = Object.create(OptimizerBase.prototype);
  PhysicalOptimizerJoinType.prototype.constructor = PhysicalOptimizerJoinType;
  PhysicalOptimizerNaive.prototype = Object.create(OptimizerBase.prototype);
  PhysicalOptimizerNaive.prototype.constructor = PhysicalOptimizerNaive;
  PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperator.prototype = Object.create(OptimizerBase.prototype);
  PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperator.prototype.constructor = PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperator;
  PhysicalOptimizerPartitionAssingPartitionsToRemaining.prototype = Object.create(OptimizerBase.prototype);
  PhysicalOptimizerPartitionAssingPartitionsToRemaining.prototype.constructor = PhysicalOptimizerPartitionAssingPartitionsToRemaining;
  PhysicalOptimizerPartitionExpandPartitionTowardsStore.prototype = Object.create(OptimizerBase.prototype);
  PhysicalOptimizerPartitionExpandPartitionTowardsStore.prototype.constructor = PhysicalOptimizerPartitionExpandPartitionTowardsStore;
  PhysicalOptimizerPartitionExpandTowardsRoot.prototype = Object.create(OptimizerBase.prototype);
  PhysicalOptimizerPartitionExpandTowardsRoot.prototype.constructor = PhysicalOptimizerPartitionExpandTowardsRoot;
  PhysicalOptimizerPartitionRemoveUselessPartitions.prototype = Object.create(OptimizerBase.prototype);
  PhysicalOptimizerPartitionRemoveUselessPartitions.prototype.constructor = PhysicalOptimizerPartitionRemoveUselessPartitions;
  PhysicalOptimizerPartitionRespectMaxPartitions.prototype = Object.create(OptimizerBase.prototype);
  PhysicalOptimizerPartitionRespectMaxPartitions.prototype.constructor = PhysicalOptimizerPartitionRespectMaxPartitions;
  PhysicalOptimizerTripleIndex.prototype = Object.create(OptimizerBase.prototype);
  PhysicalOptimizerTripleIndex.prototype.constructor = PhysicalOptimizerTripleIndex;
  PhysicalOptimizerVisualisation.prototype = Object.create(OptimizerBase.prototype);
  PhysicalOptimizerVisualisation.prototype.constructor = PhysicalOptimizerVisualisation;
  function PhysicalOptimizer(query) {
    OptimizerCompoundBase.call(this, query, 33, 'PhysicalOptimizer');
    this.childrenOptimizers_vdh5gu$_0 = [[new PhysicalOptimizerJoinType(query)], [new PhysicalOptimizerTripleIndex(query)], [new PhysicalOptimizerNaive(query)], [new PhysicalOptimizerPartitionExpandPartitionTowardsStore(query), new PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperator(query), new PhysicalOptimizerPartitionExpandTowardsRoot(query)], [new PhysicalOptimizerPartitionRespectMaxPartitions(query)], [new PhysicalOptimizerPartitionRemoveUselessPartitions(query)], [new PhysicalOptimizerPartitionAssingPartitionsToRemaining(query)], [new PhysicalOptimizerVisualisation(query)]];
  }
  Object.defineProperty(PhysicalOptimizer.prototype, 'childrenOptimizers', {
    configurable: true,
    get: function () {
      return this.childrenOptimizers_vdh5gu$_0;
    }
  });
  PhysicalOptimizer.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PhysicalOptimizer',
    interfaces: [OptimizerCompoundBase]
  };
  function PhysicalOptimizerDebug(query) {
    OptimizerBase.call(this, query, 32, 'PhysicalOptimizerDebug');
  }
  function PhysicalOptimizerDebug$optimize$lambda(closure$node, closure$parent, this$PhysicalOptimizerDebug, closure$res, closure$onChange) {
    return function () {
      if (Kotlin.isType(closure$node, POPBase) && (closure$parent == null || (!Kotlin.isType(closure$parent, POPDebug) && !Kotlin.isType(closure$parent, OPBaseCompound)))) {
        closure$res.v = new POPDebug(this$PhysicalOptimizerDebug.query, closure$node.projectedVariables, closure$node);
        closure$onChange();
      }return Unit;
    };
  }
  PhysicalOptimizerDebug.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var res = {v: node};
    if (!Kotlin.isType(node, POPDebug))
      SanityCheckOn_getInstance().invoke_ls4sck$(PhysicalOptimizerDebug$optimize$lambda(node, parent, this, res, onChange));
    return res.v;
  };
  PhysicalOptimizerDebug.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PhysicalOptimizerDebug',
    interfaces: [OptimizerBase]
  };
  function PhysicalOptimizerJoinType(query) {
    OptimizerBase.call(this, query, 34, 'PhysicalOptimizerJoinType');
  }
  PhysicalOptimizerJoinType.prototype.localGetProjected_0 = function (node, parent) {
    var tmp$;
    if (Kotlin.isType(parent, LOPProjection))
      tmp$ = parent.getProvidedVariableNames();
    else if (Kotlin.isType(parent, POPProjection))
      tmp$ = parent.getProvidedVariableNamesInternal();
    else if (Kotlin.isType(node, POPBase))
      tmp$ = node.getProvidedVariableNamesInternal();
    else {
      tmp$ = node.getProvidedVariableNames();
    }
    return tmp$;
  };
  PhysicalOptimizerJoinType.prototype.embedWithinPartitionContext_0 = function (joinColumns, childA, childB, create, keepOrder) {
    var tmp$, tmp$_0;
    if (shared.tripleStoreManager.getPartitionMode() === 2 || shared.tripleStoreManager.getPartitionMode() === 1) {
      var a = childA;
      var b = childB;
      var array = new Int32Array(joinColumns.size);
      var tmp$_1;
      tmp$_1 = array.length - 1 | 0;
      for (var i = 0; i <= tmp$_1; i++) {
        array[i] = 0;
      }
      var newID = array;
      var i_0 = joinColumns.size - 1 | 0;
      while (i_0 >= 0) {
        newID[i_0] = this.query.getNextPartitionOperatorID();
        var s = joinColumns.get_za3lpa$(i_0);
        a = new POPSplitPartition(this.query, a.getProvidedVariableNames(), s, 128, newID[i_0], a);
        b = new POPSplitPartition(this.query, b.getProvidedVariableNames(), s, 128, newID[i_0], b);
        this.query.addPartitionOperator_yhmem3$(a.uuid, newID[i_0]);
        this.query.addPartitionOperator_yhmem3$(b.uuid, newID[i_0]);
        i_0 = i_0 - 1 | 0;
      }
      i_0 = 0;
      var c = create(a, b);
      if (c.getProvidedVariableNames().isEmpty()) {
        tmp$ = joinColumns.iterator();
        while (tmp$.hasNext()) {
          var s_0 = tmp$.next();
          c = new POPMergePartitionCount(this.query, c.getProvidedVariableNames(), s_0, 128, newID[i_0], c);
          this.query.addPartitionOperator_yhmem3$(c.uuid, newID[i_0]);
          i_0 = i_0 + 1 | 0;
        }
      } else {
        tmp$_0 = joinColumns.iterator();
        while (tmp$_0.hasNext()) {
          var s_1 = tmp$_0.next();
          if (keepOrder) {
            c = new POPMergePartitionOrderedByIntId(this.query, c.getProvidedVariableNames(), s_1, 128, newID[i_0], c);
            this.query.addPartitionOperator_yhmem3$(c.uuid, newID[i_0]);
          } else {
            c = new POPMergePartition(this.query, c.getProvidedVariableNames(), s_1, 128, newID[i_0], c);
            this.query.addPartitionOperator_yhmem3$(c.uuid, newID[i_0]);
          }
          i_0 = i_0 + 1 | 0;
        }
      }
      return c;
    } else {
      return create(childA, childB);
    }
  };
  function PhysicalOptimizerJoinType$optimize$lambda(this$PhysicalOptimizerJoinType, closure$projectedVariables) {
    return function (a, b) {
      return new POPJoinMergeOptional(this$PhysicalOptimizerJoinType.query, closure$projectedVariables, a, b, true);
    };
  }
  function PhysicalOptimizerJoinType$optimize$lambda_0(this$PhysicalOptimizerJoinType, closure$projectedVariables) {
    return function (a, b) {
      return new POPJoinMergeSingleColumn(this$PhysicalOptimizerJoinType.query, closure$projectedVariables, a, b, false);
    };
  }
  function PhysicalOptimizerJoinType$optimize$lambda_1(this$PhysicalOptimizerJoinType, closure$projectedVariables) {
    return function (a, b) {
      return new POPJoinMergeOptional(this$PhysicalOptimizerJoinType.query, closure$projectedVariables, a, b, true);
    };
  }
  function PhysicalOptimizerJoinType$optimize$lambda_2(this$PhysicalOptimizerJoinType, closure$projectedVariables) {
    return function (a, b) {
      return new POPJoinMerge(this$PhysicalOptimizerJoinType.query, closure$projectedVariables, a, b, false);
    };
  }
  function PhysicalOptimizerJoinType$optimize$lambda_3(this$PhysicalOptimizerJoinType, closure$projectedVariables) {
    return function (a, b) {
      return new POPJoinMergeOptional(this$PhysicalOptimizerJoinType.query, closure$projectedVariables, a, b, true);
    };
  }
  function PhysicalOptimizerJoinType$optimize$lambda_4(this$PhysicalOptimizerJoinType, closure$projectedVariables) {
    return function (a, b) {
      return new POPJoinMerge(this$PhysicalOptimizerJoinType.query, closure$projectedVariables, a, b, false);
    };
  }
  function PhysicalOptimizerJoinType$optimize$lambda_5(this$PhysicalOptimizerJoinType, closure$projectedVariables) {
    return function (a, b) {
      return new POPJoinHashMap(this$PhysicalOptimizerJoinType.query, closure$projectedVariables, a, b, true);
    };
  }
  function PhysicalOptimizerJoinType$optimize$lambda_6(this$PhysicalOptimizerJoinType, closure$projectedVariables) {
    return function (a, b) {
      return new POPJoinHashMap(this$PhysicalOptimizerJoinType.query, closure$projectedVariables, a, b, false);
    };
  }
  function PhysicalOptimizerJoinType$optimize$lambda_7(this$PhysicalOptimizerJoinType, closure$projectedVariables) {
    return function (a, b) {
      return new POPJoinHashMap(this$PhysicalOptimizerJoinType.query, closure$projectedVariables, a, b, false);
    };
  }
  PhysicalOptimizerJoinType.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4;
    var res = node;
    var projectedVariables = this.localGetProjected_0(node, parent);
    if (Kotlin.isType(node, LOPJoin)) {
      var childA = node.children[0];
      var childB = node.children[1];
      var columns = multiinput.LOPJoin_Helper.getColumns_2mkhiy$(childA.getProvidedVariableNames(), childB.getProvidedVariableNames());
      if (columns[0].size === 0) {
        res = new POPJoinCartesianProduct(this.query, projectedVariables, childA, childB, false);
      } else {
        if (node.getMySortPriority().size >= columns[0].size) {
          if (projectedVariables.size === 1 && childA.getProvidedVariableNames().size === 1 && childB.getProvidedVariableNames().size === 1 && equals(childA.getProvidedVariableNames().get_za3lpa$(0), projectedVariables.get_za3lpa$(0)) && equals(childB.getProvidedVariableNames().get_za3lpa$(0), projectedVariables.get_za3lpa$(0))) {
            if (node.optional) {
              tmp$ = this.embedWithinPartitionContext_0(columns[0], childA, childB, PhysicalOptimizerJoinType$optimize$lambda(this, projectedVariables), true);
            } else {
              tmp$ = this.embedWithinPartitionContext_0(columns[0], childA, childB, PhysicalOptimizerJoinType$optimize$lambda_0(this, projectedVariables), true);
            }
            res = tmp$;
          } else {
            var flag = true;
            tmp$_0 = columns[0].size;
            for (var i = 0; i < tmp$_0; i++) {
              if (childA.getMySortPriority().size > i && !((tmp$_1 = childA.getMySortPriority().get_za3lpa$(i)) != null ? tmp$_1.equals(node.getMySortPriority().get_za3lpa$(i)) : null) || (childB.getMySortPriority().size > i && !((tmp$_2 = childB.getMySortPriority().get_za3lpa$(i)) != null ? tmp$_2.equals(node.getMySortPriority().get_za3lpa$(i)) : null))) {
                flag = false;
                break;
              }}
            if (flag) {
              var tmp$_5 = childA.getProvidedVariableNames();
              var $receiver = node.getMySortPriority();
              var destination = ArrayList_init(collectionSizeOrDefault($receiver, 10));
              var tmp$_6;
              tmp$_6 = $receiver.iterator();
              while (tmp$_6.hasNext()) {
                var item = tmp$_6.next();
                destination.add_11rb$(item.variableName);
              }
              if (tmp$_5.containsAll_brywnq$(destination)) {
                if (node.optional) {
                  tmp$_3 = this.embedWithinPartitionContext_0(columns[0], childA, childB, PhysicalOptimizerJoinType$optimize$lambda_1(this, projectedVariables), true);
                } else {
                  tmp$_3 = this.embedWithinPartitionContext_0(columns[0], childA, childB, PhysicalOptimizerJoinType$optimize$lambda_2(this, projectedVariables), true);
                }
              } else {
                if (node.optional) {
                  tmp$_3 = this.embedWithinPartitionContext_0(columns[0], childA, childB, PhysicalOptimizerJoinType$optimize$lambda_3(this, projectedVariables), true);
                } else {
                  tmp$_3 = this.embedWithinPartitionContext_0(columns[0], childB, childA, PhysicalOptimizerJoinType$optimize$lambda_4(this, projectedVariables), true);
                }
              }
              res = tmp$_3;
            }}
        }if (Kotlin.isType(res, LOPJoin)) {
          var keepOrder = node.getMySortPriority().size !== 0;
          if (node.optional) {
            tmp$_4 = this.embedWithinPartitionContext_0(columns[0], childA, childB, PhysicalOptimizerJoinType$optimize$lambda_5(this, projectedVariables), keepOrder);
          } else if (node.partOfAskQuery && projectedVariables.isEmpty() && Kotlin.isType(childA, LOPTriple)) {
            tmp$_4 = new POPJoinWithStoreExists(this.query, projectedVariables, childB, childA, false);
          } else if (node.partOfAskQuery && projectedVariables.isEmpty() && Kotlin.isType(childB, LOPTriple)) {
            tmp$_4 = new POPJoinWithStoreExists(this.query, projectedVariables, childA, childB, false);
          } else {
            var tmp$_7 = node.partOfAskQuery && Kotlin.isType(childA, LOPTriple) && columns[1].size > 0;
            if (tmp$_7) {
              var tmp$_8 = childB.getProvidedVariableNames();
              var $receiver_0 = node.getMySortPriority();
              var destination_0 = ArrayList_init(collectionSizeOrDefault($receiver_0, 10));
              var tmp$_9;
              tmp$_9 = $receiver_0.iterator();
              while (tmp$_9.hasNext()) {
                var item_0 = tmp$_9.next();
                destination_0.add_11rb$(item_0.variableName);
              }
              tmp$_7 = tmp$_8.containsAll_brywnq$(destination_0);
            }if (tmp$_7) {
              tmp$_4 = new POPJoinWithStore(this.query, projectedVariables, childB, childA, false);
            } else {
              var tmp$_10 = node.partOfAskQuery && Kotlin.isType(childB, LOPTriple) && columns[2].size > 0;
              if (tmp$_10) {
                var tmp$_11 = childA.getProvidedVariableNames();
                var $receiver_1 = node.getMySortPriority();
                var destination_1 = ArrayList_init(collectionSizeOrDefault($receiver_1, 10));
                var tmp$_12;
                tmp$_12 = $receiver_1.iterator();
                while (tmp$_12.hasNext()) {
                  var item_1 = tmp$_12.next();
                  destination_1.add_11rb$(item_1.variableName);
                }
                tmp$_10 = tmp$_11.containsAll_brywnq$(destination_1);
              }if (tmp$_10) {
                tmp$_4 = new POPJoinWithStore(this.query, projectedVariables, childA, childB, false);
              } else {
                var tmp$_13 = Kotlin.isType(childB, POPTripleStoreIterator) || Kotlin.isType(childB, LOPTriple);
                if (tmp$_13) {
                  var tmp$_14 = childB.getProvidedVariableNames();
                  var $receiver_2 = node.getMySortPriority();
                  var destination_2 = ArrayList_init(collectionSizeOrDefault($receiver_2, 10));
                  var tmp$_15;
                  tmp$_15 = $receiver_2.iterator();
                  while (tmp$_15.hasNext()) {
                    var item_2 = tmp$_15.next();
                    destination_2.add_11rb$(item_2.variableName);
                  }
                  tmp$_13 = tmp$_14.containsAll_brywnq$(destination_2);
                }if (tmp$_13) {
                  tmp$_4 = this.embedWithinPartitionContext_0(columns[0], childB, childA, PhysicalOptimizerJoinType$optimize$lambda_6(this, projectedVariables), keepOrder);
                } else {
                  tmp$_4 = this.embedWithinPartitionContext_0(columns[0], childA, childB, PhysicalOptimizerJoinType$optimize$lambda_7(this, projectedVariables), keepOrder);
                }
              }
            }
          }
          res = tmp$_4;
        }}
      onChange();
    }return res;
  };
  PhysicalOptimizerJoinType.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PhysicalOptimizerJoinType',
    interfaces: [OptimizerBase]
  };
  function PhysicalOptimizerNaive(query) {
    OptimizerBase.call(this, query, 35, 'PhysicalOptimizerNaive');
  }
  PhysicalOptimizerNaive.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4;
    var res = node;
    var change = true;
    var projectedVariables;
    try {
      if (Kotlin.isType(parent, LOPProjection))
        tmp$ = parent.getProvidedVariableNames();
      else if (Kotlin.isType(parent, POPProjection))
        tmp$ = parent.getProvidedVariableNamesInternal();
      else if (Kotlin.isType(node, POPBase))
        tmp$ = node.getProvidedVariableNamesInternal();
      else {
        tmp$ = node.getProvidedVariableNames();
      }
      projectedVariables = tmp$;
      if (Kotlin.isType(node, LOPSortAny)) {
        var child = node.getChildren()[0];
        var v1 = node.possibleSortOrder;
        var v2 = child.getMySortPriority();
        var flag = v1.size === v2.size;
        var i = 0;
        while (flag && i < v1.size) {
          if (!equals(v1.get_za3lpa$(i).variableName, v2.get_za3lpa$(i).variableName) || v1.get_za3lpa$(i).sortType !== v2.get_za3lpa$(i).sortType) {
            flag = false;
          }i = i + 1 | 0;
        }
        if (flag) {
          tmp$_0 = child;
        } else {
          tmp$_0 = new POPSort(this.query, projectedVariables, [], true, child);
        }
        res = tmp$_0;
      } else if (Kotlin.isType(node, LOPGraphOperation)) {
        res = new POPGraphOperation(this.query, projectedVariables, node.silent, node.graph1type, node.graph1iri, node.graph2type, node.graph2iri, node.action);
        res.sortPriorities = node.sortPriorities;
        res.mySortPriority = node.mySortPriority;
        res.sortPrioritiesInitialized = node.sortPrioritiesInitialized;
      } else if (Kotlin.isType(node, LOPModify)) {
        res = new POPModify(this.query, projectedVariables, node.insert, node.delete, node.getChildren()[0]);
        res.sortPriorities = node.sortPriorities;
        res.mySortPriority = node.mySortPriority;
        res.sortPrioritiesInitialized = node.sortPrioritiesInitialized;
      } else if (Kotlin.isType(node, LOPModifyData)) {
        res = new POPModifyData(this.query, projectedVariables, node.type, node.data);
        res.sortPriorities = node.sortPriorities;
        res.mySortPriority = node.mySortPriority;
        res.sortPrioritiesInitialized = node.sortPrioritiesInitialized;
      } else if (Kotlin.isType(node, LOPProjection)) {
        res = new POPProjection(this.query, projectedVariables, node.getChildren()[0]);
        res.sortPriorities = node.sortPriorities;
        res.mySortPriority = node.mySortPriority;
        res.sortPrioritiesInitialized = node.sortPrioritiesInitialized;
      } else if (Kotlin.isType(node, LOPMakeBooleanResult)) {
        if (Kotlin.isType(node.getChildren()[0], LOPProjection) || Kotlin.isType(node.getChildren()[0], POPProjection)) {
          tmp$_1 = new POPMakeBooleanResult(this.query, projectedVariables, node.getChildren()[0].getChildren()[0]);
        } else {
          tmp$_1 = new POPMakeBooleanResult(this.query, projectedVariables, node.getChildren()[0]);
        }
        res = tmp$_1;
        res.sortPriorities = node.sortPriorities;
        res.mySortPriority = node.mySortPriority;
        res.sortPrioritiesInitialized = node.sortPrioritiesInitialized;
      } else if (Kotlin.isType(node, LOPValues)) {
        res = POPValues_init(this.query, projectedVariables, node);
        res.sortPriorities = node.sortPriorities;
        res.mySortPriority = node.mySortPriority;
        res.sortPrioritiesInitialized = node.sortPrioritiesInitialized;
      } else if (Kotlin.isType(node, LOPLimit)) {
        res = new POPLimit(this.query, projectedVariables, node.limit, node.getChildren()[0]);
        res.sortPriorities = node.sortPriorities;
        res.mySortPriority = node.mySortPriority;
        res.sortPrioritiesInitialized = node.sortPrioritiesInitialized;
      } else if (Kotlin.isType(node, LOPReduced)) {
        res = new POPReduced(this.query, projectedVariables, node.getChildren()[0]);
        res.sortPriorities = node.sortPriorities;
        res.mySortPriority = node.mySortPriority;
        res.sortPrioritiesInitialized = node.sortPrioritiesInitialized;
      } else if (Kotlin.isType(node, LOPOffset)) {
        res = new POPOffset(this.query, projectedVariables, node.offset, node.getChildren()[0]);
        res.sortPriorities = node.sortPriorities;
        res.mySortPriority = node.mySortPriority;
        res.sortPrioritiesInitialized = node.sortPrioritiesInitialized;
      } else if (Kotlin.isType(node, LOPGroup)) {
        res = POPGroup_init(this.query, projectedVariables, node.by, node.bindings, node.getChildren()[0]);
        res.sortPriorities = node.sortPriorities;
        res.mySortPriority = node.mySortPriority;
        res.sortPrioritiesInitialized = node.sortPrioritiesInitialized;
      } else if (Kotlin.isType(node, LOPUnion)) {
        var countA = node.getChildren()[0].getChildrenCountRecoursive();
        var countB = node.getChildren()[1].getChildrenCountRecoursive();
        if (countA < countB) {
          tmp$_2 = new POPUnion(this.query, projectedVariables, node.getChildren()[0], node.getChildren()[1]);
        } else {
          tmp$_2 = new POPUnion(this.query, projectedVariables, node.getChildren()[1], node.getChildren()[0]);
        }
        res = tmp$_2;
        res.sortPriorities = node.sortPriorities;
        res.mySortPriority = node.mySortPriority;
        res.sortPrioritiesInitialized = node.sortPrioritiesInitialized;
      } else if (Kotlin.isType(node, LOPMinus)) {
        res = new POPMinus(this.query, projectedVariables, node.getChildren()[0], node.getChildren()[1]);
        res.sortPriorities = node.sortPriorities;
        res.mySortPriority = node.mySortPriority;
        res.sortPrioritiesInitialized = node.sortPrioritiesInitialized;
      } else if (Kotlin.isType(node, LOPSort))
        if (!Kotlin.isType(parent, LOPSort)) {
          var sortBy = ArrayList_init_0();
          sortBy.add_11rb$(node.by);
          var child_0 = node.getChildren()[0];
          while (Kotlin.isType(child_0, LOPSort)) {
            sortBy.add_11rb$(child_0.by);
            child_0 = child_0.getChildren()[0];
          }
          res = new POPSort(this.query, projectedVariables, copyToArray(sortBy), node.asc, child_0);
          res.sortPriorities = node.sortPriorities;
          res.mySortPriority = node.mySortPriority;
          res.sortPrioritiesInitialized = node.sortPrioritiesInitialized;
        } else {
          change = false;
        }
       else if (Kotlin.isType(node, LOPFilter)) {
        res = new POPFilter(this.query, projectedVariables, Kotlin.isType(tmp$_3 = node.getChildren()[1], AOPBase) ? tmp$_3 : throwCCE(), node.getChildren()[0]);
        res.sortPriorities = node.sortPriorities;
        res.mySortPriority = node.mySortPriority;
        res.sortPrioritiesInitialized = node.sortPrioritiesInitialized;
      } else if (Kotlin.isType(node, LOPBind)) {
        res = new POPBind(this.query, projectedVariables, node.name, Kotlin.isType(tmp$_4 = node.getChildren()[1], AOPBase) ? tmp$_4 : throwCCE(), node.getChildren()[0]);
        res.sortPriorities = node.sortPriorities;
        res.mySortPriority = node.mySortPriority;
        res.sortPrioritiesInitialized = node.sortPrioritiesInitialized;
      } else if (Kotlin.isType(node, LOPJoin)) {
        res = new POPJoinHashMap(this.query, projectedVariables, node.getChildren()[0], node.getChildren()[1], node.optional);
        res.sortPriorities = node.sortPriorities;
        res.mySortPriority = node.mySortPriority;
        res.sortPrioritiesInitialized = node.sortPrioritiesInitialized;
      } else if (Kotlin.isType(node, LOPTriple)) {
        var tmp$_5 = shared.tripleStoreManager.getGraph_61zpoe$(node.graph);
        var tmp$_6 = this.query;
        var array = Array_0(3);
        var tmp$_7;
        tmp$_7 = array.length - 1 | 0;
        for (var i_0 = 0; i_0 <= tmp$_7; i_0++) {
          var tmp$_8;
          array[i_0] = Kotlin.isType(tmp$_8 = node.getChildren()[i_0], IAOPBase) ? tmp$_8 : throwCCE();
        }
        res = tmp$_5.getIterator_8f34g7$(tmp$_6, array, 14);
      } else if (Kotlin.isType(node, OPEmptyRow)) {
        res = new POPEmptyRow(this.query, projectedVariables);
        res.sortPriorities = node.sortPriorities;
        res.mySortPriority = node.mySortPriority;
        res.sortPrioritiesInitialized = node.sortPrioritiesInitialized;
      } else {
        change = false;
      }
    }finally {
      if (change) {
        onChange();
      }}
    return res;
  };
  PhysicalOptimizerNaive.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PhysicalOptimizerNaive',
    interfaces: [OptimizerBase]
  };
  function PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperator(query) {
    OptimizerBase.call(this, query, 36, 'PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperator');
  }
  PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperator.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    if (shared.tripleStoreManager.getPartitionMode() === 2 || shared.tripleStoreManager.getPartitionMode() === 1) {
      if (Kotlin.isType(node, POPSplitPartitionFromStore)) {
        var storeNodeTmp = node.children[0];
        while (!Kotlin.isType(storeNodeTmp, POPTripleStoreIterator)) {
          storeNodeTmp = storeNodeTmp.getChildren()[0];
        }
        var storeNode = storeNodeTmp;
        var max_count = this.query.partitionOperatorCount.get_11rb$(node.partitionID);
        println('PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperator : initialize specific ' + node.getUUID().toString());
        var new_count = storeNode.changeToIndexWithMaximumPartitions_yhbofj$(max_count, node.partitionVariable);
        var $receiver = this.query.partitionOperatorCount;
        var key = node.partitionID;
        $receiver.put_xwzc9p$(key, new_count);
        node.partitionCount = new_count;
        if (new_count !== max_count) {
          onChange();
        }} else if (Kotlin.isType(node, POPSplitPartitionFromStoreCount)) {
        var storeNodeTmp_0 = node.children[0];
        while (!Kotlin.isType(storeNodeTmp_0, POPTripleStoreIterator)) {
          storeNodeTmp_0 = storeNodeTmp_0.getChildren()[0];
        }
        var storeNode_0 = storeNodeTmp_0;
        var max_count_0 = this.query.partitionOperatorCount.get_11rb$(node.partitionID);
        println('PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperator : initialize specific ' + node.getUUID().toString());
        var new_count_0 = storeNode_0.changeToIndexWithMaximumPartitions_yhbofj$(max_count_0, node.partitionVariable);
        var $receiver_0 = this.query.partitionOperatorCount;
        var key_0 = node.partitionID;
        $receiver_0.put_xwzc9p$(key_0, new_count_0);
        node.partitionCount = new_count_0;
        if (new_count_0 !== max_count_0) {
          onChange();
        }} else if (Kotlin.isType(node, POPMergePartition)) {
        var tmp = this.query.partitionOperatorCount.get_11rb$(node.partitionID);
        if (tmp != null && tmp !== node.partitionCount) {
          node.partitionCount = tmp;
          onChange();
        }} else if (Kotlin.isType(node, POPMergePartitionCount)) {
        var tmp_0 = this.query.partitionOperatorCount.get_11rb$(node.partitionID);
        if (tmp_0 != null && tmp_0 !== node.partitionCount) {
          node.partitionCount = tmp_0;
          onChange();
        }} else if (Kotlin.isType(node, POPMergePartitionOrderedByIntId)) {
        var tmp_1 = this.query.partitionOperatorCount.get_11rb$(node.partitionID);
        if (tmp_1 != null && tmp_1 !== node.partitionCount) {
          node.partitionCount = tmp_1;
          onChange();
        }} else if (Kotlin.isType(node, POPSplitPartition)) {
        var tmp_2 = this.query.partitionOperatorCount.get_11rb$(node.partitionID);
        if (tmp_2 != null && tmp_2 !== node.partitionCount) {
          node.partitionCount = tmp_2;
          onChange();
        }} else if (Kotlin.isType(node, POPChangePartitionOrderedByIntId)) {
        var tmp_3 = this.query.partitionOperatorCount.get_11rb$(node.partitionIDFrom);
        if (tmp_3 != null && tmp_3 !== node.partitionCountFrom) {
          node.partitionCountFrom = tmp_3;
          onChange();
        }var tmp2 = this.query.partitionOperatorCount.get_11rb$(node.partitionIDTo);
        if (tmp2 != null && tmp2 !== node.partitionCountTo) {
          node.partitionCountTo = tmp2;
          onChange();
        }}}return node;
  };
  PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperator.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperator',
    interfaces: [OptimizerBase]
  };
  function PhysicalOptimizerPartitionAssingPartitionsToRemaining(query) {
    OptimizerBase.call(this, query, 37, 'PhysicalOptimizerPartitionAssingPartitionsToRemaining');
  }
  PhysicalOptimizerPartitionAssingPartitionsToRemaining.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$, tmp$_0;
    var res = node;
    if (shared.tripleStoreManager.getPartitionMode() === 2 || shared.tripleStoreManager.getPartitionMode() === 1) {
      if (Kotlin.isType(node, POPTripleStoreIterator))
        if (!node.hasSplitFromStore) {
          var partitionVariable = '';
          var new_count = -1;
          tmp$ = node.children;
          for (tmp$_0 = 0; tmp$_0 !== tmp$.length; ++tmp$_0) {
            var c = tmp$[tmp$_0];
            if (Kotlin.isType(c, AOPVariable)) {
              try {
                partitionVariable = c.name;
                println('PhysicalOptimizerPartitionAssingPartitionsToRemaining : initialize specific ' + node.getUUID().toString());
                new_count = node.changeToIndexWithMaximumPartitions_yhbofj$(null, partitionVariable);
                break;
              } catch (e) {
                if (Kotlin.isType(e, Throwable)) {
                  printStackTrace(e);
                } else
                  throw e;
              }
            }}
          if (new_count > 1) {
            var partitionID = this.query.getNextPartitionOperatorID();
            println('PhysicalOptimizerPartitionAssingPartitionsToRemaining : initialize specific ' + node.getUUID().toString());
            if (node.projectedVariables.size > 0) {
              res = new POPSplitPartitionFromStore(this.query, node.projectedVariables, partitionVariable, new_count, partitionID, node);
            } else {
              res = new POPSplitPartitionFromStoreCount(this.query, node.projectedVariables, partitionVariable, new_count, partitionID, node);
            }
            this.query.addPartitionOperator_yhmem3$(res.getUUID(), partitionID);
            if (node.projectedVariables.size > 0) {
              res = new POPMergePartitionOrderedByIntId(this.query, node.projectedVariables, partitionVariable, new_count, partitionID, res);
            } else {
              res = new POPMergePartitionCount(this.query, node.projectedVariables, partitionVariable, new_count, partitionID, res);
            }
            this.query.addPartitionOperator_yhmem3$(res.getUUID(), partitionID);
            node.hasSplitFromStore = true;
            onChange();
          }}}return res;
  };
  PhysicalOptimizerPartitionAssingPartitionsToRemaining.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PhysicalOptimizerPartitionAssingPartitionsToRemaining',
    interfaces: [OptimizerBase]
  };
  function PhysicalOptimizerPartitionExpandPartitionTowardsStore(query) {
    OptimizerBase.call(this, query, 38, 'PhysicalOptimizerPartitionExpandPartitionTowardsStore');
  }
  PhysicalOptimizerPartitionExpandPartitionTowardsStore.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$;
    var res = node;
    if (shared.tripleStoreManager.getPartitionMode() === 2 || shared.tripleStoreManager.getPartitionMode() === 1) {
      if (Kotlin.isType(node, POPSplitPartition)) {
        var c = node.children[0];
        if (Kotlin.isType(c, POPReduced)) {
          res = new POPReduced(this.query, c.projectedVariables, new POPSplitPartition(this.query, c.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCount, node.partitionID, c.children[0]));
          this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionID);
          this.query.addPartitionOperator_yhmem3$(res.children[0].getUUID(), node.partitionID);
          onChange();
        } else if (Kotlin.isType(c, POPProjection)) {
          res = new POPProjection(this.query, c.projectedVariables, new POPSplitPartition(this.query, c.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCount, node.partitionID, c.children[0]));
          this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionID);
          this.query.addPartitionOperator_yhmem3$(res.children[0].getUUID(), node.partitionID);
          onChange();
        } else if (Kotlin.isType(c, POPFilter)) {
          res = new POPFilter(this.query, c.projectedVariables, Kotlin.isType(tmp$ = c.children[1], AOPBase) ? tmp$ : throwCCE(), new POPSplitPartition(this.query, c.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCount, node.partitionID, c.children[0]));
          this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionID);
          this.query.addPartitionOperator_yhmem3$(res.children[0].getUUID(), node.partitionID);
          onChange();
        } else if (Kotlin.isType(c, POPTripleStoreIterator))
          try {
            println('PhysicalOptimizerPartitionExpandPartitionTowardsStore : initialize specific ' + c.getUUID().toString());
            var new_count = c.changeToIndexWithMaximumPartitions_yhbofj$(node.partitionCount, node.partitionVariable);
            c.hasSplitFromStore = true;
            println('PhysicalOptimizerPartitionExpandPartitionTowardsStore : initialize specific ' + c.getUUID().toString());
            if (node.projectedVariables.size > 0) {
              res = new POPSplitPartitionFromStore(this.query, node.projectedVariables, node.partitionVariable, new_count, node.partitionID, c);
            } else {
              res = new POPSplitPartitionFromStoreCount(this.query, node.projectedVariables, node.partitionVariable, new_count, node.partitionID, c);
            }
            this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionID);
            this.query.addPartitionOperator_yhmem3$(res.getUUID(), node.partitionID);
            onChange();
          } catch (e) {
            if (Kotlin.isType(e, Throwable)) {
              printStackTrace(e);
            } else
              throw e;
          }
      }}return res;
  };
  PhysicalOptimizerPartitionExpandPartitionTowardsStore.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PhysicalOptimizerPartitionExpandPartitionTowardsStore',
    interfaces: [OptimizerBase]
  };
  function PhysicalOptimizerPartitionExpandTowardsRoot(query) {
    OptimizerBase.call(this, query, 39, 'PhysicalOptimizerPartitionExpandTowardsRoot');
  }
  PhysicalOptimizerPartitionExpandTowardsRoot.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4, tmp$_5;
    var res = node;
    if (shared.tripleStoreManager.getPartitionMode() === 2 || shared.tripleStoreManager.getPartitionMode() === 1) {
      if (Kotlin.isType(node, POPSplitPartitionFromStore)) {
        var storeNodeTmp = node.children[0];
        while (!Kotlin.isType(storeNodeTmp, POPTripleStoreIterator)) {
          storeNodeTmp = storeNodeTmp.getChildren()[0];
        }
        var storeNode = storeNodeTmp;
        var max_count = node.partitionCount;
        println('PhysicalOptimizerPartitionExpandTowardsRoot : initialize specific ' + node.getUUID().toString());
        var new_count = storeNode.changeToIndexWithMaximumPartitions_yhbofj$(max_count, node.partitionVariable);
        if (new_count !== max_count) {
          var newID = this.query.getNextPartitionOperatorID();
          this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionID);
          res = new POPChangePartitionOrderedByIntId(this.query, node.projectedVariables, node.partitionVariable, new_count, node.partitionCount, newID, node.partitionID, node);
          node.partitionID = newID;
          node.partitionCount = new_count;
          this.query.addPartitionOperator_yhmem3$(node.getUUID(), node.partitionID);
          this.query.addPartitionOperator_yhmem3$(res.getUUID(), res.partitionIDTo);
          this.query.addPartitionOperator_yhmem3$(res.getUUID(), res.partitionIDFrom);
          onChange();
        }} else if (Kotlin.isType(node, POPSplitPartitionFromStoreCount)) {
        var storeNodeTmp_0 = node.children[0];
        while (!Kotlin.isType(storeNodeTmp_0, POPTripleStoreIterator)) {
          storeNodeTmp_0 = storeNodeTmp_0.getChildren()[0];
        }
        var storeNode_0 = storeNodeTmp_0;
        var max_count_0 = node.partitionCount;
        println('PhysicalOptimizerPartitionExpandTowardsRoot : initialize specific ' + node.getUUID().toString());
        var new_count_0 = storeNode_0.changeToIndexWithMaximumPartitions_yhbofj$(max_count_0, node.partitionVariable);
        if (new_count_0 !== max_count_0) {
          var newID_0 = this.query.getNextPartitionOperatorID();
          this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionID);
          res = new POPChangePartitionOrderedByIntId(this.query, node.projectedVariables, node.partitionVariable, new_count_0, node.partitionCount, newID_0, node.partitionID, node);
          node.partitionID = newID_0;
          node.partitionCount = new_count_0;
          this.query.addPartitionOperator_yhmem3$(node.getUUID(), node.partitionID);
          this.query.addPartitionOperator_yhmem3$(res.getUUID(), res.partitionIDTo);
          this.query.addPartitionOperator_yhmem3$(res.getUUID(), res.partitionIDFrom);
          onChange();
        }} else if (Kotlin.isType(node, POPBind)) {
        var c = node.children[0];
        if (Kotlin.isType(c, POPMergePartition)) {
          res = new POPMergePartition(this.query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, new POPBind(this.query, node.projectedVariables, node.name, Kotlin.isType(tmp$ = node.children[1], AOPBase) ? tmp$ : throwCCE(), c.children[0]));
          this.query.removePartitionOperator_yhmem3$(c.getUUID(), c.partitionID);
          this.query.addPartitionOperator_yhmem3$(res.getUUID(), c.partitionID);
          this.query.partitionOperatorCount.clear();
          onChange();
        } else if (Kotlin.isType(c, POPMergePartitionOrderedByIntId)) {
          res = new POPMergePartitionOrderedByIntId(this.query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, new POPBind(this.query, node.projectedVariables, node.name, Kotlin.isType(tmp$_0 = node.children[1], AOPBase) ? tmp$_0 : throwCCE(), c.children[0]));
          this.query.removePartitionOperator_yhmem3$(c.getUUID(), c.partitionID);
          this.query.addPartitionOperator_yhmem3$(res.getUUID(), c.partitionID);
          this.query.partitionOperatorCount.clear();
          onChange();
        } else if (Kotlin.isType(c, POPMergePartitionCount)) {
          res = new POPMergePartitionCount(this.query, node.projectedVariables, c.partitionVariable, c.partitionCount, c.partitionID, new POPBind(this.query, node.projectedVariables, node.name, Kotlin.isType(tmp$_1 = node.children[1], AOPBase) ? tmp$_1 : throwCCE(), c.children[0]));
          this.query.removePartitionOperator_yhmem3$(c.getUUID(), c.partitionID);
          this.query.addPartitionOperator_yhmem3$(res.getUUID(), c.partitionID);
          this.query.partitionOperatorCount.clear();
          onChange();
        }} else if (Kotlin.isType(node, POPUnion)) {
        var c0 = node.children[0];
        var c1 = node.children[1];
        var modeC0 = 0;
        var modeC1 = 0;
        var columnNameC0 = '';
        var columnCountC0 = 0;
        var columnIDC0 = 0;
        var columnNameC1 = '';
        var columnCountC1 = 0;
        var columnIDC1 = 0;
        if (Kotlin.isType(c0, POPMergePartition)) {
          modeC0 = 1;
          columnNameC0 = c0.partitionVariable;
          columnCountC0 = c0.partitionCount;
          columnIDC0 = c0.partitionID;
        } else if (Kotlin.isType(c0, POPMergePartitionOrderedByIntId)) {
          modeC0 = 2;
          columnNameC0 = c0.partitionVariable;
          columnCountC0 = c0.partitionCount;
          columnIDC0 = c0.partitionID;
        } else if (Kotlin.isType(c0, POPMergePartitionCount)) {
          modeC0 = 3;
          columnNameC0 = c0.partitionVariable;
          columnCountC0 = c0.partitionCount;
          columnIDC0 = c0.partitionID;
        }if (Kotlin.isType(c1, POPMergePartition)) {
          modeC1 = 1;
          columnNameC1 = c1.partitionVariable;
          columnCountC1 = c1.partitionCount;
          columnIDC1 = c1.partitionID;
        } else if (Kotlin.isType(c1, POPMergePartitionOrderedByIntId)) {
          modeC1 = 2;
          columnNameC1 = c1.partitionVariable;
          columnCountC1 = c1.partitionCount;
          columnIDC1 = c1.partitionID;
        } else if (Kotlin.isType(c1, POPMergePartitionCount)) {
          modeC1 = 3;
          columnNameC1 = c1.partitionVariable;
          columnCountC1 = c1.partitionCount;
          columnIDC1 = c1.partitionID;
        }if (modeC0 === modeC1 && equals(columnNameC0, columnNameC1) && modeC0 > 0) {
          if (columnCountC0 === columnCountC1) {
            var columnID = this.query.mergePartitionOperator_meq4y1$(columnIDC0, columnIDC1, c1.getChildren()[0]);
            switch (modeC0) {
              case 1:
                res = new POPMergePartition(this.query, node.projectedVariables, columnNameC0, columnCountC0, columnID, new POPUnion(this.query, node.projectedVariables, c0.getChildren()[0], c1.getChildren()[0]));
                this.query.removePartitionOperator_yhmem3$(c0.getUUID(), columnID);
                this.query.removePartitionOperator_yhmem3$(c1.getUUID(), columnID);
                this.query.addPartitionOperator_yhmem3$(res.getUUID(), columnID);
                this.query.partitionOperatorCount.clear();
                onChange();
                break;
              case 2:
                res = new POPMergePartitionOrderedByIntId(this.query, node.projectedVariables, columnNameC0, columnCountC0, columnID, new POPUnion(this.query, node.projectedVariables, c0.getChildren()[0], c1.getChildren()[0]));
                this.query.removePartitionOperator_yhmem3$(c0.getUUID(), columnID);
                this.query.removePartitionOperator_yhmem3$(c1.getUUID(), columnID);
                this.query.addPartitionOperator_yhmem3$(res.getUUID(), columnID);
                this.query.partitionOperatorCount.clear();
                onChange();
                break;
              case 3:
                res = new POPMergePartitionCount(this.query, node.projectedVariables, columnNameC0, columnCountC0, columnID, new POPUnion(this.query, node.projectedVariables, c0.getChildren()[0], c1.getChildren()[0]));
                this.query.removePartitionOperator_yhmem3$(c0.getUUID(), columnID);
                this.query.removePartitionOperator_yhmem3$(c1.getUUID(), columnID);
                this.query.addPartitionOperator_yhmem3$(res.getUUID(), columnID);
                this.query.partitionOperatorCount.clear();
                onChange();
                break;
              default:throw Exception_init('not reachable - implementation error');
            }
          } else {
            throw Exception_init('not implemented ... column counts are different');
          }
        }} else if (Kotlin.isType(node, POPProjection)) {
        var c_0 = node.children[0];
        if (Kotlin.isType(c_0, POPMergePartition)) {
          res = new POPMergePartition(this.query, node.projectedVariables, c_0.partitionVariable, c_0.partitionCount, c_0.partitionID, new POPProjection(this.query, node.projectedVariables, c_0.children[0]));
          this.query.removePartitionOperator_yhmem3$(c_0.getUUID(), c_0.partitionID);
          this.query.addPartitionOperator_yhmem3$(res.getUUID(), c_0.partitionID);
          this.query.partitionOperatorCount.clear();
          onChange();
        } else if (Kotlin.isType(c_0, POPMergePartitionOrderedByIntId)) {
          res = new POPMergePartitionOrderedByIntId(this.query, node.projectedVariables, c_0.partitionVariable, c_0.partitionCount, c_0.partitionID, new POPProjection(this.query, node.projectedVariables, c_0.children[0]));
          this.query.removePartitionOperator_yhmem3$(c_0.getUUID(), c_0.partitionID);
          this.query.addPartitionOperator_yhmem3$(res.getUUID(), c_0.partitionID);
          this.query.partitionOperatorCount.clear();
          onChange();
        } else if (Kotlin.isType(c_0, POPMergePartitionCount)) {
          res = new POPMergePartitionCount(this.query, node.projectedVariables, c_0.partitionVariable, c_0.partitionCount, c_0.partitionID, new POPProjection(this.query, node.projectedVariables, c_0.children[0]));
          this.query.removePartitionOperator_yhmem3$(c_0.getUUID(), c_0.partitionID);
          this.query.addPartitionOperator_yhmem3$(res.getUUID(), c_0.partitionID);
          this.query.partitionOperatorCount.clear();
          onChange();
        }} else if (Kotlin.isType(node, POPReduced)) {
        var c_1 = node.children[0];
        if (Kotlin.isType(c_1, POPMergePartition)) {
          res = new POPMergePartition(this.query, node.projectedVariables, c_1.partitionVariable, c_1.partitionCount, c_1.partitionID, new POPReduced(this.query, node.projectedVariables, c_1.children[0]));
          this.query.removePartitionOperator_yhmem3$(c_1.getUUID(), c_1.partitionID);
          this.query.addPartitionOperator_yhmem3$(res.getUUID(), c_1.partitionID);
          this.query.partitionOperatorCount.clear();
          onChange();
        } else if (Kotlin.isType(c_1, POPMergePartitionOrderedByIntId)) {
          res = new POPMergePartitionOrderedByIntId(this.query, node.projectedVariables, c_1.partitionVariable, c_1.partitionCount, c_1.partitionID, new POPReduced(this.query, node.projectedVariables, c_1.children[0]));
          this.query.removePartitionOperator_yhmem3$(c_1.getUUID(), c_1.partitionID);
          this.query.addPartitionOperator_yhmem3$(res.getUUID(), c_1.partitionID);
          this.query.partitionOperatorCount.clear();
          onChange();
        } else if (Kotlin.isType(c_1, POPMergePartitionCount)) {
          res = new POPMergePartitionCount(this.query, node.projectedVariables, c_1.partitionVariable, c_1.partitionCount, c_1.partitionID, new POPReduced(this.query, node.projectedVariables, c_1.children[0]));
          this.query.removePartitionOperator_yhmem3$(c_1.getUUID(), c_1.partitionID);
          this.query.addPartitionOperator_yhmem3$(res.getUUID(), c_1.partitionID);
          this.query.partitionOperatorCount.clear();
          onChange();
        }} else if (Kotlin.isType(node, POPFilter)) {
        var c_2 = node.children[0];
        if (Kotlin.isType(c_2, POPMergePartition)) {
          res = new POPMergePartition(this.query, node.projectedVariables, c_2.partitionVariable, c_2.partitionCount, c_2.partitionID, new POPFilter(this.query, node.projectedVariables, Kotlin.isType(tmp$_2 = node.children[1], AOPBase) ? tmp$_2 : throwCCE(), c_2.children[0]));
          this.query.removePartitionOperator_yhmem3$(c_2.getUUID(), c_2.partitionID);
          this.query.addPartitionOperator_yhmem3$(res.getUUID(), c_2.partitionID);
          this.query.partitionOperatorCount.clear();
          onChange();
        } else if (Kotlin.isType(c_2, POPMergePartitionOrderedByIntId)) {
          res = new POPMergePartitionOrderedByIntId(this.query, node.projectedVariables, c_2.partitionVariable, c_2.partitionCount, c_2.partitionID, new POPFilter(this.query, node.projectedVariables, Kotlin.isType(tmp$_3 = node.children[1], AOPBase) ? tmp$_3 : throwCCE(), c_2.children[0]));
          this.query.removePartitionOperator_yhmem3$(c_2.getUUID(), c_2.partitionID);
          this.query.addPartitionOperator_yhmem3$(res.getUUID(), c_2.partitionID);
          this.query.partitionOperatorCount.clear();
          onChange();
        } else if (Kotlin.isType(c_2, POPMergePartitionCount)) {
          res = new POPMergePartitionCount(this.query, node.projectedVariables, c_2.partitionVariable, c_2.partitionCount, c_2.partitionID, new POPFilter(this.query, node.projectedVariables, Kotlin.isType(tmp$_4 = node.children[1], AOPBase) ? tmp$_4 : throwCCE(), c_2.children[0]));
          this.query.removePartitionOperator_yhmem3$(c_2.getUUID(), c_2.partitionID);
          this.query.addPartitionOperator_yhmem3$(res.getUUID(), c_2.partitionID);
          this.query.partitionOperatorCount.clear();
          onChange();
        }} else if (Kotlin.isType(node, POPSplitPartition)) {
        var c_3 = node.children[0];
        if (Kotlin.isType(c_3, POPMergePartition)) {
          if (equals(node.partitionVariable, c_3.partitionVariable)) {
            if (node.partitionCount === c_3.partitionCount) {
              res = c_3.children[0];
              this.query.removePartitionOperator_yhmem3$(c_3.getUUID(), c_3.partitionID);
              this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionID);
              this.query.mergePartitionOperator_meq4y1$(node.partitionID, c_3.partitionID, res);
              this.query.partitionOperatorCount.clear();
              onChange();
            } else if (node.partitionCount < c_3.partitionCount) {
              this.query.removePartitionOperator_yhmem3$(c_3.getUUID(), c_3.partitionID);
              this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionID);
              res = new POPChangePartitionOrderedByIntId(this.query, node.projectedVariables, node.partitionVariable, c_3.partitionCount, node.partitionCount, c_3.partitionID, node.partitionID, c_3.children[0]);
              this.query.addPartitionOperator_yhmem3$(res.getUUID(), res.partitionIDTo);
              this.query.addPartitionOperator_yhmem3$(res.getUUID(), res.partitionIDFrom);
              onChange();
            }}} else if (Kotlin.isType(c_3, POPMergePartitionOrderedByIntId)) {
          if (equals(node.partitionVariable, c_3.partitionVariable)) {
            if (node.partitionCount === c_3.partitionCount) {
              res = c_3.children[0];
              this.query.removePartitionOperator_yhmem3$(c_3.getUUID(), c_3.partitionID);
              this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionID);
              this.query.mergePartitionOperator_meq4y1$(node.partitionID, c_3.partitionID, res);
              this.query.partitionOperatorCount.clear();
              onChange();
            } else if (node.partitionCount < c_3.partitionCount) {
              this.query.removePartitionOperator_yhmem3$(c_3.getUUID(), c_3.partitionID);
              this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionID);
              res = new POPChangePartitionOrderedByIntId(this.query, node.projectedVariables, node.partitionVariable, c_3.partitionCount, node.partitionCount, c_3.partitionID, node.partitionID, c_3.children[0]);
              this.query.addPartitionOperator_yhmem3$(res.getUUID(), res.partitionIDTo);
              this.query.addPartitionOperator_yhmem3$(res.getUUID(), res.partitionIDFrom);
              onChange();
            }}} else if (Kotlin.isType(c_3, POPMergePartitionCount)) {
          if (equals(node.partitionVariable, c_3.partitionVariable)) {
            if (node.partitionCount === c_3.partitionCount) {
              res = c_3.children[0];
              this.query.removePartitionOperator_yhmem3$(c_3.getUUID(), c_3.partitionID);
              this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionID);
              this.query.mergePartitionOperator_meq4y1$(node.partitionID, c_3.partitionID, res);
              this.query.partitionOperatorCount.clear();
              onChange();
            }}} else if (Kotlin.isType(c_3, POPReduced)) {
          res = new POPReduced(this.query, c_3.projectedVariables, new POPSplitPartition(this.query, c_3.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCount, node.partitionID, c_3.children[0]));
          this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionID);
          this.query.addPartitionOperator_yhmem3$(res.children[0].getUUID(), node.partitionID);
          this.query.partitionOperatorCount.clear();
          onChange();
        } else if (Kotlin.isType(c_3, POPProjection)) {
          res = new POPProjection(this.query, c_3.projectedVariables, new POPSplitPartition(this.query, c_3.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCount, node.partitionID, c_3.children[0]));
          this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionID);
          this.query.addPartitionOperator_yhmem3$(res.children[0].getUUID(), node.partitionID);
          this.query.partitionOperatorCount.clear();
          onChange();
        } else if (Kotlin.isType(c_3, POPFilter)) {
          res = new POPFilter(this.query, c_3.projectedVariables, Kotlin.isType(tmp$_5 = c_3.children[1], AOPBase) ? tmp$_5 : throwCCE(), new POPSplitPartition(this.query, c_3.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCount, node.partitionID, c_3.children[0]));
          this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionID);
          this.query.addPartitionOperator_yhmem3$(res.children[0].getUUID(), node.partitionID);
          this.query.partitionOperatorCount.clear();
          onChange();
        } else if (Kotlin.isType(c_3, POPTripleStoreIterator))
          try {
            println('PhysicalOptimizerPartitionExpandTowardsRoot : initialize specific b ' + c_3.getUUID().toString());
            var new_count_1 = c_3.changeToIndexWithMaximumPartitions_yhbofj$(node.partitionCount, node.partitionVariable);
            c_3.hasSplitFromStore = true;
            if (node.projectedVariables.size > 0) {
              res = new POPSplitPartitionFromStore(this.query, node.projectedVariables, node.partitionVariable, new_count_1, node.partitionID, c_3);
            } else {
              res = new POPSplitPartitionFromStoreCount(this.query, node.projectedVariables, node.partitionVariable, new_count_1, node.partitionID, c_3);
            }
            this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionID);
            this.query.addPartitionOperator_yhmem3$(res.getUUID(), node.partitionID);
            onChange();
          } catch (e) {
            if (Kotlin.isType(e, Throwable)) {
              printStackTrace(e);
            } else
              throw e;
          }
      }}return res;
  };
  PhysicalOptimizerPartitionExpandTowardsRoot.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PhysicalOptimizerPartitionExpandTowardsRoot',
    interfaces: [OptimizerBase]
  };
  function PhysicalOptimizerPartitionRemoveUselessPartitions(query) {
    OptimizerBase.call(this, query, 40, 'PhysicalOptimizerPartitionRemoveUselessPartitions');
  }
  PhysicalOptimizerPartitionRemoveUselessPartitions.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var res = node;
    if (shared.tripleStoreManager.getPartitionMode() === 2 || shared.tripleStoreManager.getPartitionMode() === 1) {
      if (Kotlin.isType(node, POPSplitPartitionFromStore)) {
        if (node.partitionCount === 1) {
          res = node.children[0];
          var storeNodeTmp = node.children[0];
          while (!Kotlin.isType(storeNodeTmp, POPTripleStoreIterator)) {
            storeNodeTmp = storeNodeTmp.getChildren()[0];
          }
          var storeNode = storeNodeTmp;
          storeNode.hasSplitFromStore = false;
          println('PhysicalOptimizerPartitionRemoveUselessPartitions : initialize specific ' + node.getUUID().toString());
          this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionID);
          onChange();
        }} else if (Kotlin.isType(node, POPSplitPartitionFromStoreCount)) {
        if (node.partitionCount === 1) {
          res = node.children[0];
          var storeNodeTmp_0 = node.children[0];
          while (!Kotlin.isType(storeNodeTmp_0, POPTripleStoreIterator)) {
            storeNodeTmp_0 = storeNodeTmp_0.getChildren()[0];
          }
          var storeNode_0 = storeNodeTmp_0;
          storeNode_0.hasSplitFromStore = false;
          println('PhysicalOptimizerPartitionRemoveUselessPartitions : initialize specific ' + node.getUUID().toString());
          this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionID);
          onChange();
        }} else if (Kotlin.isType(node, POPSplitPartition)) {
        if (node.partitionCount === 1) {
          res = node.children[0];
          this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionID);
          onChange();
        }} else if (Kotlin.isType(node, POPMergePartition)) {
        if (node.partitionCount === 1) {
          res = node.children[0];
          this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionID);
          onChange();
        }} else if (Kotlin.isType(node, POPMergePartitionCount)) {
        if (node.partitionCount === 1) {
          res = node.children[0];
          this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionID);
          onChange();
        }} else if (Kotlin.isType(node, POPMergePartitionOrderedByIntId)) {
        if (node.partitionCount === 1) {
          res = node.children[0];
          this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionID);
          onChange();
        }} else if (Kotlin.isType(node, POPChangePartitionOrderedByIntId))
        if (node.partitionCountFrom === 1 && node.partitionCountTo === 1) {
          res = node.children[0];
          this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionIDFrom);
          this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionIDTo);
          onChange();
        } else if (node.partitionCountFrom === 1) {
          res = new POPSplitPartition(this.query, node.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCountTo, node.partitionIDTo, node.children[0]);
          this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionIDFrom);
          this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionIDTo);
          this.query.addPartitionOperator_yhmem3$(res.getUUID(), res.partitionID);
          onChange();
        } else if (node.partitionCountTo === 1) {
          res = new POPMergePartitionOrderedByIntId(this.query, node.children[0].getProvidedVariableNames(), node.partitionVariable, node.partitionCountFrom, node.partitionIDFrom, node.children[0]);
          this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionIDFrom);
          this.query.removePartitionOperator_yhmem3$(node.getUUID(), node.partitionIDTo);
          this.query.addPartitionOperator_yhmem3$(res.getUUID(), res.partitionID);
          onChange();
        }}return res;
  };
  PhysicalOptimizerPartitionRemoveUselessPartitions.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PhysicalOptimizerPartitionRemoveUselessPartitions',
    interfaces: [OptimizerBase]
  };
  function PhysicalOptimizerPartitionRespectMaxPartitions(query) {
    OptimizerBase.call(this, query, 41, 'PhysicalOptimizerPartitionRespectMaxPartitions');
  }
  PhysicalOptimizerPartitionRespectMaxPartitions.prototype.getNumberOfEnclosingPartitions_0 = function (node) {
    var count = 1;
    var childs = node.getChildren();
    if (!(childs.length === 0)) {
      var tmp = this.getNumberOfEnclosingPartitions_0(childs[0]);
      count = tmp;
    }if (Kotlin.isType(node, POPSplitPartitionFromStore))
      count = Kotlin.imul(count, node.partitionCount);
    else if (Kotlin.isType(node, POPSplitPartitionFromStoreCount))
      count = Kotlin.imul(count, node.partitionCount);
    else if (Kotlin.isType(node, POPSplitPartition))
      count = Kotlin.imul(count, node.partitionCount);
    else if (Kotlin.isType(node, POPChangePartitionOrderedByIntId))
      count = Kotlin.imul(count, node.partitionCountTo) / node.partitionCountFrom | 0;
    else if (Kotlin.isType(node, POPMergePartition))
      count = count / node.partitionCount | 0;
    else if (Kotlin.isType(node, POPMergePartitionCount))
      count = count / node.partitionCount | 0;
    else if (Kotlin.isType(node, POPMergePartitionOrderedByIntId))
      count = count / node.partitionCount | 0;
    return count;
  };
  PhysicalOptimizerPartitionRespectMaxPartitions.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2;
    if (shared.tripleStoreManager.getPartitionMode() === 2 || shared.tripleStoreManager.getPartitionMode() === 1) {
      if (Kotlin.isType(node, POPSplitPartitionFromStore)) {
        var tmp = this.query.partitionOperatorCount.get_11rb$(node.partitionID);
        if (tmp != null && tmp !== node.partitionCount) {
          node.partitionCount = tmp;
          onChange();
        }var $receiver = this.query.partitionOperatorCount;
        var key = node.partitionID;
        var value = node.partitionCount;
        $receiver.put_xwzc9p$(key, value);
        var newCount = node.partitionCount;
        var count = Kotlin.imul(this.getNumberOfEnclosingPartitions_0(node.children[0]), node.partitionCount);
        if (count > 128) {
          var reduceFactor = count / 128 | 0;
          if (reduceFactor > node.partitionCount) {
            tmp$ = 1;
          } else {
            tmp$ = node.partitionCount / reduceFactor | 0;
          }
          newCount = tmp$;
        }if (newCount < node.partitionCount) {
          node.partitionCount = newCount;
          var $receiver_0 = this.query.partitionOperatorCount;
          var key_0 = node.partitionID;
          var value_0 = newCount;
          $receiver_0.put_xwzc9p$(key_0, value_0);
          onChange();
        }} else if (Kotlin.isType(node, POPSplitPartitionFromStoreCount)) {
        var tmp_0 = this.query.partitionOperatorCount.get_11rb$(node.partitionID);
        if (tmp_0 != null && tmp_0 !== node.partitionCount) {
          node.partitionCount = tmp_0;
          onChange();
        }var $receiver_1 = this.query.partitionOperatorCount;
        var key_1 = node.partitionID;
        var value_1 = node.partitionCount;
        $receiver_1.put_xwzc9p$(key_1, value_1);
        var newCount_0 = node.partitionCount;
        var count_0 = Kotlin.imul(this.getNumberOfEnclosingPartitions_0(node.children[0]), node.partitionCount);
        if (count_0 > 128) {
          var reduceFactor_0 = count_0 / 128 | 0;
          if (reduceFactor_0 > node.partitionCount) {
            tmp$_0 = 1;
          } else {
            tmp$_0 = node.partitionCount / reduceFactor_0 | 0;
          }
          newCount_0 = tmp$_0;
        }if (newCount_0 < node.partitionCount) {
          node.partitionCount = newCount_0;
          var $receiver_2 = this.query.partitionOperatorCount;
          var key_2 = node.partitionID;
          var value_2 = newCount_0;
          $receiver_2.put_xwzc9p$(key_2, value_2);
          onChange();
        }} else if (Kotlin.isType(node, POPSplitPartition)) {
        var tmp_1 = this.query.partitionOperatorCount.get_11rb$(node.partitionID);
        if (tmp_1 != null && tmp_1 !== node.partitionCount) {
          node.partitionCount = tmp_1;
          onChange();
        }var $receiver_3 = this.query.partitionOperatorCount;
        var key_3 = node.partitionID;
        var value_3 = node.partitionCount;
        $receiver_3.put_xwzc9p$(key_3, value_3);
        var newCount_1 = node.partitionCount;
        var count_1 = Kotlin.imul(this.getNumberOfEnclosingPartitions_0(node.children[0]), node.partitionCount);
        if (count_1 > 128) {
          var reduceFactor_1 = count_1 / 128 | 0;
          if (reduceFactor_1 > node.partitionCount) {
            tmp$_1 = 1;
          } else {
            tmp$_1 = node.partitionCount / reduceFactor_1 | 0;
          }
          newCount_1 = tmp$_1;
        }if (newCount_1 < node.partitionCount) {
          node.partitionCount = newCount_1;
          var $receiver_4 = this.query.partitionOperatorCount;
          var key_4 = node.partitionID;
          var value_4 = newCount_1;
          $receiver_4.put_xwzc9p$(key_4, value_4);
          onChange();
        }} else if (Kotlin.isType(node, POPMergePartition)) {
        var tmp_2 = this.query.partitionOperatorCount.get_11rb$(node.partitionID);
        if (tmp_2 != null && tmp_2 !== node.partitionCount) {
          node.partitionCount = tmp_2;
          onChange();
        }} else if (Kotlin.isType(node, POPMergePartitionCount)) {
        var tmp_3 = this.query.partitionOperatorCount.get_11rb$(node.partitionID);
        if (tmp_3 != null && tmp_3 !== node.partitionCount) {
          node.partitionCount = tmp_3;
          onChange();
        }} else if (Kotlin.isType(node, POPMergePartitionOrderedByIntId)) {
        var tmp_4 = this.query.partitionOperatorCount.get_11rb$(node.partitionID);
        if (tmp_4 != null && tmp_4 !== node.partitionCount) {
          node.partitionCount = tmp_4;
          onChange();
        }} else if (Kotlin.isType(node, POPChangePartitionOrderedByIntId)) {
        var tmp_5 = this.query.partitionOperatorCount.get_11rb$(node.partitionIDFrom);
        if (tmp_5 != null && tmp_5 !== node.partitionCountFrom) {
          node.partitionCountFrom = tmp_5;
          onChange();
        }var tmp2 = this.query.partitionOperatorCount.get_11rb$(node.partitionIDTo);
        if (tmp2 != null && tmp2 !== node.partitionCountTo) {
          node.partitionCountTo = tmp2;
          onChange();
        }var $receiver_5 = this.query.partitionOperatorCount;
        var key_5 = node.partitionIDTo;
        var value_5 = node.partitionCountTo;
        $receiver_5.put_xwzc9p$(key_5, value_5);
        var newCount_2 = node.partitionCountTo;
        var count_2 = Kotlin.imul(this.getNumberOfEnclosingPartitions_0(node.children[0]), node.partitionCountTo) / node.partitionCountFrom | 0;
        if (count_2 > 128) {
          var reduceFactor_2 = count_2 / 128 | 0;
          if (reduceFactor_2 > node.partitionCountTo) {
            tmp$_2 = 1;
          } else {
            tmp$_2 = node.partitionCountTo / reduceFactor_2 | 0;
          }
          newCount_2 = tmp$_2;
        }if (newCount_2 < node.partitionCountTo) {
          node.partitionCountTo = newCount_2;
          var $receiver_6 = this.query.partitionOperatorCount;
          var key_6 = node.partitionIDTo;
          var value_6 = newCount_2;
          $receiver_6.put_xwzc9p$(key_6, value_6);
          onChange();
        }}}return node;
  };
  PhysicalOptimizerPartitionRespectMaxPartitions.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PhysicalOptimizerPartitionRespectMaxPartitions',
    interfaces: [OptimizerBase]
  };
  function PhysicalOptimizerTripleIndex(query) {
    OptimizerBase.call(this, query, 42, 'PhysicalOptimizerTripleIndex');
  }
  function PhysicalOptimizerTripleIndex$optimize$lambda$lambda$lambda(closure$projectedVariables, closure$res2) {
    return function () {
      return closure$projectedVariables.contains_11rb$(closure$res2.name) || equals(closure$res2.name, '_');
    };
  }
  function PhysicalOptimizerTripleIndex$optimize$lambda$lambda(closure$res2, closure$projectedVariables) {
    return function () {
      if (Kotlin.isType(closure$res2, AOPVariable)) {
        SanityCheckOn_getInstance().check_8i7tro$(PhysicalOptimizerTripleIndex$optimize$lambda$lambda$lambda(closure$projectedVariables, closure$res2));
      }return Unit;
    };
  }
  function PhysicalOptimizerTripleIndex$optimize$lambda$lambda_0(closure$node, closure$i) {
    return function () {
      return closure$node.mySortPriority.get_za3lpa$(closure$i).sortType === 2;
    };
  }
  function PhysicalOptimizerTripleIndex$optimize$lambda(closure$node) {
    return function () {
      var tmp$;
      tmp$ = closure$node.mySortPriority.size;
      for (var i = 0; i < tmp$; i++) {
        SanityCheckOn_getInstance().check_8i7tro$(PhysicalOptimizerTripleIndex$optimize$lambda$lambda_0(closure$node, i));
      }
      return Unit;
    };
  }
  PhysicalOptimizerTripleIndex.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var tmp$;
    var res = node;
    if (Kotlin.isType(node, LOPTriple)) {
      if (Kotlin.isType(parent, LOPProjection))
        tmp$ = parent.getProvidedVariableNames();
      else if (Kotlin.isType(parent, POPProjection))
        tmp$ = parent.getProvidedVariableNamesInternal();
      else if (Kotlin.isType(node, POPBase))
        tmp$ = node.getProvidedVariableNamesInternal();
      else {
        tmp$ = node.getProvidedVariableNames();
      }
      var projectedVariables = tmp$;
      onChange();
      var store = shared.tripleStoreManager.getGraph_61zpoe$(node.graph);
      var array = Array_0(3);
      var tmp$_0;
      tmp$_0 = array.length - 1 | 0;
      for (var i = 0; i <= tmp$_0; i++) {
        var tmp$_1;
        var res2 = Kotlin.isType(tmp$_1 = node.children[i], AOPBase) ? tmp$_1 : throwCCE();
        SanityCheckOn_getInstance().invoke_ls4sck$(PhysicalOptimizerTripleIndex$optimize$lambda$lambda(res2, projectedVariables));
        array[i] = res2;
      }
      var params = array;
      SanityCheckOn_getInstance().invoke_ls4sck$(PhysicalOptimizerTripleIndex$optimize$lambda(node));
      var tmp$_2 = LOPTriple.Companion;
      var tmp$_3 = node.children;
      var $receiver = node.mySortPriority;
      var destination = ArrayList_init(collectionSizeOrDefault($receiver, 10));
      var tmp$_4;
      tmp$_4 = $receiver.iterator();
      while (tmp$_4.hasNext()) {
        var item = tmp$_4.next();
        destination.add_11rb$(item.variableName);
      }
      var targetIdx = tmp$_2.getIndex_t4zqhu$(tmp$_3, destination);
      res = store.getIterator_8f34g7$(this.query, params, targetIdx);
      if (Kotlin.isType(res, POPTripleStoreIterator)) {
        res.sortPriorities = node.sortPriorities;
        res.mySortPriority = node.mySortPriority;
        res.sortPrioritiesInitialized = node.sortPrioritiesInitialized;
      }}return res;
  };
  PhysicalOptimizerTripleIndex.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PhysicalOptimizerTripleIndex',
    interfaces: [OptimizerBase]
  };
  function PhysicalOptimizerVisualisation(query) {
    OptimizerBase.call(this, query, 43, 'PhysicalOptimizerVisualisation');
  }
  PhysicalOptimizerVisualisation.prototype.optimize_stqclm$ = function (node, parent, onChange) {
    var res = node;
    if (!Kotlin.isType(node, POPVisualisation))
      if (Kotlin.isType(node, POPBase) && !Kotlin.isType(parent, POPVisualisation)) {
        res = new POPVisualisation(this.query, node.projectedVariables, node);
        if (parent != null) {
          res.setParent_tpi62f$(parent);
        }onChange();
      }return res;
  };
  PhysicalOptimizerVisualisation.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'PhysicalOptimizerVisualisation',
    interfaces: [OptimizerBase]
  };
  function BufferManagerPage() {
    BufferManagerPage_instance = this;
    this.BUFFER_MANAGER_PAGE_SIZE_IN_BYTES_8be2vx$ = 8192;
  }
  BufferManagerPage.prototype.create_8be2vx$ = function () {
    var data = new Int8Array(8196);
    this.setPageID_pao7sd$(data, -1);
    return data;
  };
  function BufferManagerPage$copyInto$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.copyInto_v35ddt$ = function (data, destination, destinationOffset, startIndex, endIndex) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$copyInto$lambda(data, this));
    arrayCopy(data, destination, destinationOffset, startIndex, endIndex);
  };
  function BufferManagerPage$copyFrom$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.copyFrom_v35ddt$ = function (data, source, destinationOffset, startIndex, endIndex) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$copyFrom$lambda(data, this));
    arrayCopy(source, data, destinationOffset, startIndex, endIndex);
  };
  BufferManagerPage.prototype.getPageID_ma41of$ = function (data) {
    return ByteArrayHelper_getInstance().readInt4_pao7sd$(data, 8192);
  };
  function BufferManagerPage$setPageID$lambda(closure$value, closure$data, this$BufferManagerPage) {
    return function () {
      return closure$value === -1 || this$BufferManagerPage.getPageID_ma41of$(closure$data) === -1;
    };
  }
  BufferManagerPage.prototype.setPageID_pao7sd$ = function (data, value) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$setPageID$lambda(value, data, this));
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(data, 8192, value);
  };
  function BufferManagerPage$writeInt1$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.writeInt1_qibw1t$ = function (data, offset, value) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$writeInt1$lambda(data, this));
    ByteArrayHelper_getInstance().writeInt1_qibw1t$(data, offset, value);
  };
  function BufferManagerPage$writeInt2$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.writeInt2_qibw1t$ = function (data, offset, value) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$writeInt2$lambda(data, this));
    ByteArrayHelper_getInstance().writeInt2_qibw1t$(data, offset, value);
  };
  function BufferManagerPage$writeInt3$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.writeInt3_qibw1t$ = function (data, offset, value) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$writeInt3$lambda(data, this));
    ByteArrayHelper_getInstance().writeInt3_qibw1t$(data, offset, value);
  };
  function BufferManagerPage$writeInt4$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.writeInt4_qibw1t$ = function (data, offset, value) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$writeInt4$lambda(data, this));
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(data, offset, value);
  };
  BufferManagerPage.prototype.writeIntX_4f9ssz$ = function (data, offset, value, count) {
    switch (count) {
      case 0:
        break;
      case 1:
        this.writeInt1_qibw1t$(data, offset, value);
        break;
      case 2:
        this.writeInt2_qibw1t$(data, offset, value);
        break;
      case 3:
        this.writeInt3_qibw1t$(data, offset, value);
        break;
      default:this.writeInt4_qibw1t$(data, offset, value);
        break;
    }
  };
  function BufferManagerPage$writeLong8$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.writeLong8_ul24ie$ = function (data, offset, value) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$writeLong8$lambda(data, this));
    ByteArrayHelper_getInstance().writeLong8_ul24ie$(data, offset, value);
  };
  function BufferManagerPage$writeChar$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.writeChar_ul80vw$ = function (data, offset, value) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$writeChar$lambda(data, this));
    ByteArrayHelper_getInstance().writeChar_ul80vw$(data, offset, value);
  };
  function BufferManagerPage$readLong8$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.readLong8_pao7sd$ = function (data, offset) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$readLong8$lambda(data, this));
    return ByteArrayHelper_getInstance().readLong8_pao7sd$(data, offset);
  };
  function BufferManagerPage$readInt4$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.readInt4_pao7sd$ = function (data, offset) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$readInt4$lambda(data, this));
    return ByteArrayHelper_getInstance().readInt4_pao7sd$(data, offset);
  };
  function BufferManagerPage$readInt3$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.readInt3_pao7sd$ = function (data, offset) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$readInt3$lambda(data, this));
    return ByteArrayHelper_getInstance().readInt3_pao7sd$(data, offset);
  };
  function BufferManagerPage$readInt2$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.readInt2_pao7sd$ = function (data, offset) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$readInt2$lambda(data, this));
    return ByteArrayHelper_getInstance().readInt2_pao7sd$(data, offset);
  };
  function BufferManagerPage$readInt1$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.readInt1_pao7sd$ = function (data, offset) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$readInt1$lambda(data, this));
    return ByteArrayHelper_getInstance().readInt1_pao7sd$(data, offset);
  };
  BufferManagerPage.prototype.readIntX_qibw1t$ = function (data, offset, count) {
    switch (count) {
      case 0:
        return 0;
      case 1:
        return this.readInt1_pao7sd$(data, offset);
      case 2:
        return this.readInt2_pao7sd$(data, offset);
      case 3:
        return this.readInt3_pao7sd$(data, offset);
      default:return this.readInt4_pao7sd$(data, offset);
    }
  };
  function BufferManagerPage$readChar$lambda(closure$data, this$BufferManagerPage) {
    return function () {
      return this$BufferManagerPage.getPageID_ma41of$(closure$data) !== -1;
    };
  }
  BufferManagerPage.prototype.readChar_pao7sd$ = function (data, offset) {
    SanityCheckOn_getInstance().check_8i7tro$(BufferManagerPage$readChar$lambda(data, this));
    return ByteArrayHelper_getInstance().readChar_pao7sd$(data, offset);
  };
  BufferManagerPage.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'BufferManagerPage',
    interfaces: []
  };
  var BufferManagerPage_instance = null;
  function BufferManagerPage_getInstance() {
    if (BufferManagerPage_instance === null) {
      new BufferManagerPage();
    }return BufferManagerPage_instance;
  }
  function ColumnIteratorQueueExt() {
    ColumnIteratorQueueExt_instance = this;
  }
  ColumnIteratorQueueExt.prototype._close_6z1dri$ = function (it) {
    if (it.label !== 0) {
      it.label = 0;
      it.queue.clear();
    }};
  ColumnIteratorQueueExt.prototype.nextHelper_tdqia6$ = function (it, onEmptyQueue, onClose) {
    var tmp$, tmp$_0;
    switch (it.label) {
      case 1:
        if (it.queue.size === 0) {
          onEmptyQueue();
          if (it.queue.size > 0) {
            tmp$ = it.queue.removeAt_za3lpa$(0);
          } else {
            onClose();
            tmp$ = 4;
          }
        } else {
          tmp$ = it.queue.removeAt_za3lpa$(0);
        }

        return tmp$;
      case 2:
        if (it.queue.size === 0) {
          onClose();
          tmp$_0 = 4;
        } else {
          tmp$_0 = it.queue.removeAt_za3lpa$(0);
        }

        return tmp$_0;
      default:return 4;
    }
  };
  ColumnIteratorQueueExt.prototype.closeOnEmptyQueue_6z1dri$ = function (it) {
    if (it.label !== 0) {
      it.label = 2;
    }};
  ColumnIteratorQueueExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ColumnIteratorQueueExt',
    interfaces: []
  };
  var ColumnIteratorQueueExt_instance = null;
  function ColumnIteratorQueueExt_getInstance() {
    if (ColumnIteratorQueueExt_instance === null) {
      new ColumnIteratorQueueExt();
    }return ColumnIteratorQueueExt_instance;
  }
  function DictionaryHelper() {
    DictionaryHelper_instance = this;
  }
  DictionaryHelper.prototype.errorToByteArray_b1q5io$ = function (buffer) {
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 4);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 5);
  };
  DictionaryHelper.prototype.undefToByteArray_b1q5io$ = function (buffer) {
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 4);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 12);
  };
  DictionaryHelper.prototype.dateTimeToByteArray_akwfwi$ = function (buffer, str) {
    var year;
    var month;
    var day;
    var hours;
    var minutes;
    var seconds;
    var timezoneHours;
    var timezoneMinutes;
    var idx = 0;
    var idx2 = indexOf(str, 45, 1);
    if (idx2 < idx) {
      idx2 = str.length - 1 | 0;
    }if (idx2 > idx) {
      var startIndex = idx;
      var endIndex = idx2;
      year = str.substring(startIndex, endIndex);
      idx = idx2;
      idx2 = indexOf(str, 45, idx + 1 | 0);
      if (idx2 < idx) {
        idx2 = str.length - 1 | 0;
      }if (idx2 > idx) {
        var startIndex_0 = idx + 1 | 0;
        var endIndex_0 = idx2;
        month = toInt(str.substring(startIndex_0, endIndex_0));
        idx = idx2;
        idx2 = indexOf(str, 84, idx + 1 | 0);
        if (idx2 < idx) {
          idx2 = str.length - 1 | 0;
        }if (idx2 > idx) {
          var startIndex_1 = idx + 1 | 0;
          var endIndex_1 = idx2;
          day = toInt(str.substring(startIndex_1, endIndex_1));
          idx = idx2;
          idx2 = indexOf(str, 58, idx + 1 | 0);
          if (idx2 < idx) {
            idx2 = str.length - 1 | 0;
          }if (idx2 > idx) {
            var startIndex_2 = idx + 1 | 0;
            var endIndex_2 = idx2;
            hours = toInt(str.substring(startIndex_2, endIndex_2));
            idx = idx2;
            idx2 = indexOf(str, 58, idx + 1 | 0);
            if (idx2 < idx) {
              idx2 = str.length - 1 | 0;
            }if (idx2 > idx) {
              var startIndex_3 = idx + 1 | 0;
              var endIndex_3 = idx2;
              minutes = toInt(str.substring(startIndex_3, endIndex_3));
              idx = idx2;
              var idxa = indexOf(str, 90, idx + 1 | 0);
              var idxb = indexOf(str, 43, idx + 1 | 0);
              var idxc = indexOf(str, 45, idx + 1 | 0);
              if (idxa > idx) {
                var startIndex_4 = idx + 1 | 0;
                seconds = str.substring(startIndex_4, idxa);
                timezoneHours = 0;
                timezoneMinutes = 0;
              } else if (idxb > idx) {
                var startIndex_5 = idx + 1 | 0;
                seconds = str.substring(startIndex_5, idxb);
                idx = idxb;
                idx2 = indexOf(str, 58, idx + 1 | 0);
                if (idx2 > idx) {
                  var startIndex_6 = idx;
                  var endIndex_4 = idx2;
                  timezoneHours = toInt(str.substring(startIndex_6, endIndex_4));
                  var startIndex_7 = idx2 + 1 | 0;
                  var endIndex_5 = str.length;
                  timezoneMinutes = toInt(str.substring(startIndex_7, endIndex_5));
                } else {
                  timezoneHours = -99;
                  timezoneMinutes = -99;
                }
              } else if (idxc > idx) {
                var startIndex_8 = idx + 1 | 0;
                seconds = str.substring(startIndex_8, idxc);
                idx = idxc;
                idx2 = indexOf(str, 58, idx + 1 | 0);
                if (idx2 > idx) {
                  var startIndex_9 = idx;
                  var endIndex_6 = idx2;
                  timezoneHours = toInt(str.substring(startIndex_9, endIndex_6));
                  var startIndex_10 = idx2 + 1 | 0;
                  var endIndex_7 = str.length;
                  timezoneMinutes = toInt(str.substring(startIndex_10, endIndex_7));
                } else {
                  timezoneHours = -99;
                  timezoneMinutes = -99;
                }
              } else {
                var startIndex_11 = idx + 1 | 0;
                var endIndex_8 = str.length;
                seconds = str.substring(startIndex_11, endIndex_8);
                timezoneHours = -99;
                timezoneMinutes = -99;
              }
            } else {
              minutes = 0;
              seconds = '0.0';
              timezoneHours = -99;
              timezoneMinutes = -99;
            }
          } else {
            hours = 0;
            minutes = 0;
            seconds = '0.0';
            timezoneHours = -99;
            timezoneMinutes = -99;
          }
        } else {
          day = 0;
          hours = 0;
          minutes = 0;
          seconds = '0.0';
          timezoneHours = -99;
          timezoneMinutes = -99;
        }
      } else {
        month = 0;
        day = 0;
        hours = 0;
        minutes = 0;
        seconds = '0.0';
        timezoneHours = -99;
        timezoneMinutes = -99;
      }
    } else {
      year = '0';
      month = 0;
      day = 0;
      hours = 0;
      minutes = 0;
      seconds = '0.0';
      timezoneHours = -99;
      timezoneMinutes = -99;
    }
    this.dateTimeToByteArray_o4mgi8$(buffer, BigInteger.Companion.parseString_bm4lxs$(year, 10), month, day, hours, minutes, BigDecimal.Companion.parseString_bm4lxs$(seconds, 10), timezoneHours, timezoneMinutes);
  };
  function DictionaryHelper$dateTimeToByteArray$lambda(closure$month) {
    return function () {
      return closure$month >= 0;
    };
  }
  function DictionaryHelper$dateTimeToByteArray$lambda_0(closure$month) {
    return function () {
      return closure$month <= 99;
    };
  }
  function DictionaryHelper$dateTimeToByteArray$lambda_1(closure$day) {
    return function () {
      return closure$day >= 0;
    };
  }
  function DictionaryHelper$dateTimeToByteArray$lambda_2(closure$day) {
    return function () {
      return closure$day <= 99;
    };
  }
  function DictionaryHelper$dateTimeToByteArray$lambda_3(closure$hours) {
    return function () {
      return closure$hours >= 0;
    };
  }
  function DictionaryHelper$dateTimeToByteArray$lambda_4(closure$hours) {
    return function () {
      return closure$hours <= 24;
    };
  }
  function DictionaryHelper$dateTimeToByteArray$lambda_5(closure$minutes) {
    return function () {
      return closure$minutes >= 0;
    };
  }
  function DictionaryHelper$dateTimeToByteArray$lambda_6(closure$minutes) {
    return function () {
      return closure$minutes <= 99;
    };
  }
  function DictionaryHelper$dateTimeToByteArray$lambda_7(closure$timezoneHours) {
    return function () {
      return closure$timezoneHours >= -24;
    };
  }
  function DictionaryHelper$dateTimeToByteArray$lambda_8(closure$timezoneHours) {
    return function () {
      return closure$timezoneHours <= 24;
    };
  }
  function DictionaryHelper$dateTimeToByteArray$lambda_9(closure$timezoneMinutes) {
    return function () {
      return closure$timezoneMinutes >= 0;
    };
  }
  function DictionaryHelper$dateTimeToByteArray$lambda_10(closure$timezoneMinutes) {
    return function () {
      return closure$timezoneMinutes <= 99;
    };
  }
  function DictionaryHelper$dateTimeToByteArray$lambda_11(closure$off, closure$buffer) {
    return function () {
      return closure$off.v === closure$buffer.size;
    };
  }
  DictionaryHelper.prototype.dateTimeToByteArray_o4mgi8$ = function (buffer, year, month, day, hours, minutes, seconds, timezoneHours, timezoneMinutes) {
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda(month));
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda_0(month));
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda_1(day));
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda_2(day));
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda_3(hours));
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda_4(hours));
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda_5(minutes));
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda_6(minutes));
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda_7(timezoneHours));
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda_8(timezoneHours));
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda_9(timezoneMinutes));
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda_10(timezoneMinutes));
    var buf1 = year.toByteArray();
    var buf2 = seconds.significand.toByteArray();
    var l1 = buf1.length;
    var l2 = buf2.length;
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 42 + l1 + l2 | 0);
    var off = {v: 0};
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, off.v, 2);
    off.v = off.v + 4 | 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, off.v, l1);
    off.v = off.v + 4 | 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, off.v, month);
    off.v = off.v + 4 | 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, off.v, day);
    off.v = off.v + 4 | 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, off.v, hours);
    off.v = off.v + 4 | 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, off.v, minutes);
    off.v = off.v + 4 | 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, off.v, timezoneHours);
    off.v = off.v + 4 | 0;
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, off.v, timezoneMinutes);
    off.v = off.v + 4 | 0;
    ByteArrayHelper_getInstance().writeLong8_ul24ie$(buffer.buf, off.v, seconds.exponent);
    off.v = off.v + 8 | 0;
    buffer.buf[off.v] = toByte(year.signum());
    off.v = off.v + 1 | 0;
    buffer.buf[off.v] = toByte(seconds.signum());
    off.v = off.v + 1 | 0;
    arrayCopy(buf1, buffer.buf, off.v, 0, buf1.length);
    off.v = off.v + l1 | 0;
    arrayCopy(buf2, buffer.buf, off.v, 0, buf2.length);
    off.v = off.v + l2 | 0;
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$dateTimeToByteArray$lambda_11(off, buffer));
  };
  function DictionaryHelper$byteArrayToDateTime_Year$lambda(closure$off, closure$buffer) {
    return function () {
      return closure$off.v === closure$buffer.size;
    };
  }
  DictionaryHelper.prototype.byteArrayToDateTime_Year_b1q5io$ = function (buffer) {
    var tmp$;
    var off = {v: 0};
    off.v = off.v + 4 | 0;
    var l1 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off.v);
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 8 | 0;
    switch (buffer.buf[off.v]) {
      case -1:
        tmp$ = Sign.NEGATIVE;
        break;
      case 1:
        tmp$ = Sign.POSITIVE;
        break;
      default:tmp$ = Sign.ZERO;
        break;
    }
    var yearSignum = tmp$;
    off.v = off.v + 1 | 0;
    off.v = off.v + 1 | 0;
    var buf1 = new Int8Array(l1);
    arrayCopy(buffer.buf, buf1, 0, off.v, off.v + l1 | 0);
    off.v = off.v + l1 | 0;
    var l2 = buffer.size - l1 - 42 | 0;
    off.v = off.v + l2 | 0;
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$byteArrayToDateTime_Year$lambda(off, buffer));
    var year = BigInteger.Companion.fromByteArray_cz08zj$(buf1, yearSignum);
    return year;
  };
  DictionaryHelper.prototype.byteArrayToDateTime_Month_b1q5io$ = function (buffer) {
    var off = 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    var month = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off);
    return BigInteger_init(month);
  };
  DictionaryHelper.prototype.byteArrayToDateTime_Day_b1q5io$ = function (buffer) {
    var off = 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    var day = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off);
    return BigInteger_init(day);
  };
  DictionaryHelper.prototype.byteArrayToDateTime_Hours_b1q5io$ = function (buffer) {
    var off = 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    var hours = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off);
    return BigInteger_init(hours);
  };
  DictionaryHelper.prototype.byteArrayToDateTime_Minutes_b1q5io$ = function (buffer) {
    var off = 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    var minutes = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off);
    return BigInteger_init(minutes);
  };
  function DictionaryHelper$byteArrayToDateTime_Seconds$lambda(closure$off, closure$buffer) {
    return function () {
      return closure$off.v === closure$buffer.size;
    };
  }
  DictionaryHelper.prototype.byteArrayToDateTime_Seconds_b1q5io$ = function (buffer) {
    var tmp$;
    var off = {v: 0};
    off.v = off.v + 4 | 0;
    var l1 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off.v);
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    off.v = off.v + 4 | 0;
    var secondsExponent = ByteArrayHelper_getInstance().readLong8_pao7sd$(buffer.buf, off.v);
    off.v = off.v + 8 | 0;
    off.v = off.v + 1 | 0;
    switch (buffer.buf[off.v]) {
      case -1:
        tmp$ = Sign.NEGATIVE;
        break;
      case 1:
        tmp$ = Sign.POSITIVE;
        break;
      default:tmp$ = Sign.ZERO;
        break;
    }
    var secondsSignum = tmp$;
    off.v = off.v + 1 | 0;
    off.v = off.v + l1 | 0;
    var l2 = buffer.size - l1 - 42 | 0;
    var buf2 = new Int8Array(l2);
    arrayCopy(buffer.buf, buf2, 0, off.v, off.v + l2 | 0);
    arrayCopy(buf2, buffer.buf, off.v, 0, buf2.length);
    off.v = off.v + l2 | 0;
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$byteArrayToDateTime_Seconds$lambda(off, buffer));
    var seconds = BigDecimal.Companion.fromBigIntegerWithExponent_2w0s5z$(BigInteger.Companion.fromByteArray_cz08zj$(buf2, secondsSignum), secondsExponent);
    return seconds;
  };
  function DictionaryHelper$byteArrayToDateTimeAsTyped_Content$lambda(closure$off, closure$buffer) {
    return function () {
      return closure$off.v === closure$buffer.size;
    };
  }
  DictionaryHelper.prototype.byteArrayToDateTimeAsTyped_Content_b1q5io$ = function (buffer) {
    var tmp$, tmp$_0, tmp$_1;
    var off = {v: 0};
    off.v = off.v + 4 | 0;
    var l1 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off.v);
    off.v = off.v + 4 | 0;
    var month = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off.v);
    off.v = off.v + 4 | 0;
    var day = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off.v);
    off.v = off.v + 4 | 0;
    var hours = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off.v);
    off.v = off.v + 4 | 0;
    var minutes = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off.v);
    off.v = off.v + 4 | 0;
    var timezoneHours = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off.v);
    off.v = off.v + 4 | 0;
    var timezoneMinutes = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off.v);
    off.v = off.v + 4 | 0;
    var secondsExponent = ByteArrayHelper_getInstance().readLong8_pao7sd$(buffer.buf, off.v);
    off.v = off.v + 8 | 0;
    switch (buffer.buf[off.v]) {
      case -1:
        tmp$ = Sign.NEGATIVE;
        break;
      case 1:
        tmp$ = Sign.POSITIVE;
        break;
      default:tmp$ = Sign.ZERO;
        break;
    }
    var yearSignum = tmp$;
    off.v = off.v + 1 | 0;
    switch (buffer.buf[off.v]) {
      case -1:
        tmp$_0 = Sign.NEGATIVE;
        break;
      case 1:
        tmp$_0 = Sign.POSITIVE;
        break;
      default:tmp$_0 = Sign.ZERO;
        break;
    }
    var secondsSignum = tmp$_0;
    off.v = off.v + 1 | 0;
    var buf1 = new Int8Array(l1);
    arrayCopy(buffer.buf, buf1, 0, off.v, off.v + l1 | 0);
    off.v = off.v + l1 | 0;
    var l2 = buffer.size - l1 - 42 | 0;
    var buf2 = new Int8Array(l2);
    arrayCopy(buffer.buf, buf2, 0, off.v, off.v + l2 | 0);
    arrayCopy(buf2, buffer.buf, off.v, 0, buf2.length);
    off.v = off.v + l2 | 0;
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$byteArrayToDateTimeAsTyped_Content$lambda(off, buffer));
    var year = BigInteger.Companion.fromByteArray_cz08zj$(buf1, yearSignum);
    var seconds = BigDecimal.Companion.fromBigIntegerWithExponent_2w0s5z$(BigInteger.Companion.fromByteArray_cz08zj$(buf2, secondsSignum), secondsExponent);
    var secondsString2 = split(seconds.toStringExpanded(), ['.']);
    var secondsString = padStart(secondsString2.get_za3lpa$(0), 2, 48);
    if (secondsString2.size > 1) {
      var tmp = secondsString2.get_za3lpa$(1);
      while (endsWith(tmp, 48)) {
        var $receiver = tmp;
        var endIndex = tmp.length - 1 | 0;
        tmp = $receiver.substring(0, endIndex);
      }
      if (tmp.length > 0) {
        secondsString += '.' + tmp;
      }}if (timezoneHours === -99 && timezoneMinutes === -99) {
      tmp$_1 = year.toString() + '-' + padStart(month.toString(), 2, 48) + '-' + padStart(day.toString(), 2, 48) + 'T' + padStart(hours.toString(), 2, 48) + ':' + padStart(minutes.toString(), 2, 48) + ':' + secondsString;
    } else if (timezoneHours === 0 && timezoneMinutes === 0) {
      tmp$_1 = year.toString() + '-' + padStart(month.toString(), 2, 48) + '-' + padStart(day.toString(), 2, 48) + 'T' + padStart(hours.toString(), 2, 48) + ':' + padStart(minutes.toString(), 2, 48) + ':' + secondsString + 'Z';
    } else {
      var timezoneHoursLocal = timezoneHours.toString();
      if (timezoneHoursLocal.charCodeAt(0) === 45 || timezoneHoursLocal.charCodeAt(0) === 43) {
        timezoneHoursLocal = '' + String.fromCharCode(toBoxedChar(timezoneHoursLocal.charCodeAt(0))) + padStart(timezoneHoursLocal.substring(1), 2, 48);
      } else {
        timezoneHoursLocal = '+' + padStart(timezoneHoursLocal, 2, 48);
      }
      tmp$_1 = year.toString() + '-' + padStart(month.toString(), 2, 48) + '-' + padStart(day.toString(), 2, 48) + 'T' + padStart(hours.toString(), 2, 48) + ':' + padStart(minutes.toString(), 2, 48) + ':' + secondsString + timezoneHoursLocal + ':' + padStart(timezoneMinutes.toString(), 2, 48);
    }
    return tmp$_1;
  };
  DictionaryHelper.prototype.byteArrayToDateTime_TZ_b1q5io$ = function (buffer) {
    var off = 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    var timezoneHours = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off);
    off = off + 4 | 0;
    var timezoneMinutes = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off);
    if (timezoneHours === 0 && timezoneMinutes === 0) {
      return 'Z';
    }if (timezoneHours === -1 && timezoneMinutes === -1) {
      return '';
    }return '-' + padStart(timezoneHours.toString(), 2, 48) + ':' + padStart(timezoneMinutes.toString(), 2, 48);
  };
  DictionaryHelper.prototype.byteArrayToDateTime_TimeZone_b1q5io$ = function (buffer) {
    var off = 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    off = off + 4 | 0;
    var timezoneHours = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off);
    off = off + 4 | 0;
    var timezoneMinutes = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, off);
    if (timezoneHours === 0 && timezoneMinutes === 0) {
      return '"PT0S"^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>';
    }if (timezoneHours >= 0 && timezoneMinutes === 0) {
      return '"' + '-PT' + timezoneHours + 'H' + '"' + '^^<http://www.w3.org/2001/XMLSchema#dayTimeDuration>';
    }return '';
  };
  DictionaryHelper.prototype.booleanToByteArray_5191p3$ = function (buffer, value) {
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 5);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 1);
    if (value) {
      buffer.buf[4] = 1;
    } else {
      buffer.buf[4] = 0;
    }
  };
  DictionaryHelper.prototype.byteArrayToBoolean_b1q5io$ = function (buffer) {
    return buffer.buf[4] !== toByte(0);
  };
  DictionaryHelper.prototype.integerToByteArray_akwfwi$ = function (buffer, value) {
    this.integerToByteArray_ddz2hi$(buffer, BigInteger.Companion.parseString_bm4lxs$(value, 10));
  };
  DictionaryHelper.prototype.integerToByteArray_ddz2hi$ = function (buffer, value) {
    var buf1 = value.toByteArray();
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 5 + buf1.length | 0);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 7);
    buffer.buf[4] = toByte(value.signum());
    arrayCopy(buf1, buffer.buf, 5, 0, buf1.length);
  };
  DictionaryHelper.prototype.byteArrayToInteger_S_b1q5io$ = function (buffer) {
    return this.byteArrayToInteger_I_b1q5io$(buffer).toString();
  };
  DictionaryHelper.prototype.byteArrayToInteger_I_b1q5io$ = function (buffer) {
    var tmp$;
    var l1 = buffer.size - 5 | 0;
    var buf = new Int8Array(l1);
    arrayCopy(buffer.buf, buf, 0, 5, 5 + l1 | 0);
    switch (buffer.buf[4]) {
      case -1:
        tmp$ = Sign.NEGATIVE;
        break;
      case 1:
        tmp$ = Sign.POSITIVE;
        break;
      default:tmp$ = Sign.ZERO;
        break;
    }
    var sign = tmp$;
    return BigInteger.Companion.fromByteArray_cz08zj$(buf, sign);
  };
  DictionaryHelper.prototype.decimalToByteArray_akwfwi$ = function (buffer, value) {
    this.decimalToByteArray_g73zp2$(buffer, BigDecimal.Companion.parseString_bm4lxs$(value, 10));
  };
  DictionaryHelper.prototype.decimalToByteArray_g73zp2$ = function (buffer, value) {
    var buf1 = value.significand.toByteArray();
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 13 + buf1.length | 0);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 3);
    ByteArrayHelper_getInstance().writeLong8_ul24ie$(buffer.buf, 4, value.exponent);
    buffer.buf[12] = toByte(value.signum());
    arrayCopy(buf1, buffer.buf, 13, 0, buf1.length);
  };
  DictionaryHelper.prototype.byteArrayToDecimal_I_b1q5io$ = function (buffer) {
    var tmp$;
    var l1 = buffer.size - 13 | 0;
    var buf = new Int8Array(l1);
    arrayCopy(buffer.buf, buf, 0, 13, 13 + l1 | 0);
    var exponent = ByteArrayHelper_getInstance().readLong8_pao7sd$(buffer.buf, 4);
    switch (buffer.buf[12]) {
      case -1:
        tmp$ = Sign.NEGATIVE;
        break;
      case 1:
        tmp$ = Sign.POSITIVE;
        break;
      default:tmp$ = Sign.ZERO;
        break;
    }
    var sign = tmp$;
    return BigDecimal.Companion.fromBigIntegerWithExponent_2w0s5z$(BigInteger.Companion.fromByteArray_cz08zj$(buf, sign), exponent);
  };
  DictionaryHelper.prototype.byteArrayToDecimal_S_b1q5io$ = function (buffer) {
    var tmp = this.byteArrayToDecimal_I_b1q5io$(buffer).toStringExpanded();
    if (contains(tmp, 46)) {
      return tmp;
    }return tmp + '.0';
  };
  DictionaryHelper.prototype.doubleToByteArray_3eiwqq$ = function (buffer, value) {
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 12);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 4);
    ByteArrayHelper_getInstance().writeDouble8_aunrlr$(buffer.buf, 4, value);
  };
  DictionaryHelper.prototype.doubleToByteArray_akwfwi$ = function (buffer, value) {
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 12);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 4);
    ByteArrayHelper_getInstance().writeDouble8_aunrlr$(buffer.buf, 4, toDouble(value));
  };
  DictionaryHelper.prototype.byteArrayToDouble_I_b1q5io$ = function (buffer) {
    return ByteArrayHelper_getInstance().readDouble8_pao7sd$(buffer.buf, 4);
  };
  DictionaryHelper.prototype.byteArrayToDouble_S_b1q5io$ = function (buffer) {
    return ByteArrayHelper_getInstance().readDouble8_pao7sd$(buffer.buf, 4).toString();
  };
  DictionaryHelper.prototype.floatToByteArray_3eiwqq$ = function (buffer, value) {
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 12);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 6);
    ByteArrayHelper_getInstance().writeDouble8_aunrlr$(buffer.buf, 4, value);
  };
  DictionaryHelper.prototype.floatToByteArray_akwfwi$ = function (buffer, value) {
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 12);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 6);
    ByteArrayHelper_getInstance().writeDouble8_aunrlr$(buffer.buf, 4, toDouble(value));
  };
  DictionaryHelper.prototype.byteArrayToFloat_I_b1q5io$ = function (buffer) {
    return ByteArrayHelper_getInstance().readDouble8_pao7sd$(buffer.buf, 4);
  };
  DictionaryHelper.prototype.byteArrayToFloat_S_b1q5io$ = function (buffer) {
    return ByteArrayHelper_getInstance().readDouble8_pao7sd$(buffer.buf, 4).toString();
  };
  DictionaryHelper.prototype.langToByteArray_v5q3o4$ = function (buffer, content, lang) {
    var buf1 = encodeToByteArray(lang);
    var buf2 = encodeToByteArray(content);
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 9 + buf1.length + buf2.length | 0);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 10);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 5 + buf1.length + buf2.length | 0, buf1.length);
    arrayCopy(buf1, buffer.buf, 4, 0, buf1.length);
    buffer.buf[4 + buf1.length | 0] = 0;
    arrayCopy(buf2, buffer.buf, 5 + buf1.length | 0, 0, buf2.length);
  };
  DictionaryHelper.prototype.byteArrayToLang_Content_b1q5io$ = function (buffer) {
    var l1 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, buffer.size - 4 | 0);
    var l2 = buffer.size - 9 - l1 | 0;
    var buf = new Int8Array(l2);
    arrayCopy(buffer.buf, buf, 0, 5 + l1 | 0, 5 + l1 + l2 | 0);
    return decodeToString(buf);
  };
  DictionaryHelper.prototype.byteArrayToLang_Lang_b1q5io$ = function (buffer) {
    var l1 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, buffer.size - 4 | 0);
    var buf = new Int8Array(l1);
    arrayCopy(buffer.buf, buf, 0, 4, 4 + l1 | 0);
    return decodeToString(buf);
  };
  DictionaryHelper.prototype.typedToByteArray_v5q3o4$ = function (buffer, content, type) {
    try {
      switch (type) {
        case 'http://www.w3.org/2001/XMLSchema#integer':
          this.integerToByteArray_akwfwi$(buffer, content);
          break;
        case 'http://www.w3.org/2001/XMLSchema#decimal':
          this.decimalToByteArray_akwfwi$(buffer, content);
          break;
        case 'http://www.w3.org/2001/XMLSchema#double':
          this.doubleToByteArray_3eiwqq$(buffer, toDouble(content));
          break;
        case 'http://www.w3.org/2001/XMLSchema#float':
          this.floatToByteArray_3eiwqq$(buffer, toDouble(content));
          break;
        case 'http://www.w3.org/2001/XMLSchema#boolean':
          this.booleanToByteArray_5191p3$(buffer, equals(content.toLowerCase(), 'true'));
          break;
        case 'http://www.w3.org/2001/XMLSchema#dateTime':
          this.dateTimeToByteArray_akwfwi$(buffer, content);
          break;
        default:var buf1 = encodeToByteArray(type);
          var buf2 = encodeToByteArray(content);
          ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 9 + buf1.length + buf2.length | 0);
          ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 11);
          ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 5 + buf1.length + buf2.length | 0, buf1.length);
          arrayCopy(buf1, buffer.buf, 4, 0, buf1.length);
          buffer.buf[4 + buf1.length | 0] = 0;
          arrayCopy(buf2, buffer.buf, 5 + buf1.length | 0, 0, buf2.length);
          break;
      }
    } catch (e) {
      if (Kotlin.isType(e, Exception)) {
        printStackTrace(e);
        this.stringToByteArray_akwfwi$(buffer, content);
      } else
        throw e;
    }
  };
  DictionaryHelper.prototype.byteArrayToTyped_Content_b1q5io$ = function (buffer) {
    var l1 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, buffer.size - 4 | 0);
    var l2 = buffer.size - 9 - l1 | 0;
    var buf = new Int8Array(l2);
    arrayCopy(buffer.buf, buf, 0, 5 + l1 | 0, 5 + l1 + l2 | 0);
    return decodeToString(buf);
  };
  DictionaryHelper.prototype.byteArrayToTyped_Type_b1q5io$ = function (buffer) {
    var l1 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, buffer.size - 4 | 0);
    var buf = new Int8Array(l1);
    arrayCopy(buffer.buf, buf, 0, 4, 4 + l1 | 0);
    return decodeToString(buf);
  };
  function DictionaryHelper$bnodeToByteArray$lambda(closure$value) {
    return function () {
      return closure$value.length > 0;
    };
  }
  DictionaryHelper.prototype.bnodeToByteArray_akwfwi$ = function (buffer, value) {
    SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$bnodeToByteArray$lambda(value));
    var buf1 = encodeToByteArray(value);
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 8 + buf1.length | 0);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 0);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 4, buf1.length);
    arrayCopy(buf1, buffer.buf, 8, 0, buf1.length);
  };
  DictionaryHelper.prototype.bnodeToByteArray_v5fxe$ = function (buffer, value) {
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 8);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 0);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 4, value);
  };
  DictionaryHelper.prototype.byteArrayToBnode_I_b1q5io$ = function (buffer) {
    if (buffer.size === 8) {
      return ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, 4);
    } else {
      throw Exception_init('this is not ready to be used as instanciated value');
    }
  };
  DictionaryHelper.prototype.byteArrayToBnode_S_b1q5io$ = function (buffer) {
    if (buffer.size === 8) {
      throw Exception_init('this is not ready to be used as import value');
    } else {
      var l1 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, 4);
      var buf = new Int8Array(l1);
      arrayCopy(buffer.buf, buf, 0, 8, 8 + l1 | 0);
      return decodeToString(buf);
    }
  };
  DictionaryHelper.prototype.byteArrayToBnode_A_b1q5io$ = function (buffer) {
    if (buffer.size === 8) {
      return ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, 4).toString();
    } else {
      var l1 = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, 4);
      var buf = new Int8Array(l1);
      arrayCopy(buffer.buf, buf, 0, 8, 8 + l1 | 0);
      return decodeToString(buf);
    }
  };
  DictionaryHelper.prototype.iriToByteArray_akwfwi$ = function (buffer, value) {
    var buf1 = encodeToByteArray(value);
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 4 + buf1.length | 0);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 8);
    arrayCopy(buf1, buffer.buf, 4, 0, buf1.length);
  };
  DictionaryHelper.prototype.byteArrayToIri_b1q5io$ = function (buffer) {
    var l1 = buffer.size - 4 | 0;
    var buf = new Int8Array(l1);
    arrayCopy(buffer.buf, buf, 0, 4, 4 + l1 | 0);
    return decodeToString(buf);
  };
  DictionaryHelper.prototype.byteArrayToString_b1q5io$ = function (buffer) {
    var l1 = buffer.size - 4 | 0;
    var buf = new Int8Array(l1);
    arrayCopy(buffer.buf, buf, 0, 4, 4 + l1 | 0);
    return decodeToString(buf);
  };
  DictionaryHelper.prototype.stringToByteArray_akwfwi$ = function (buffer, value) {
    var buf1 = encodeToByteArray(value);
    ByteArrayWrapperExt_getInstance().setSize_v5fxe$(buffer, 4 + buf1.length | 0);
    ByteArrayHelper_getInstance().writeInt4_qibw1t$(buffer.buf, 0, 9);
    arrayCopy(buf1, buffer.buf, 4, 0, buf1.length);
  };
  function DictionaryHelper$sparqlToByteArray$lambda(closure$langIdx) {
    return function () {
      return closure$langIdx > 0;
    };
  }
  DictionaryHelper.prototype.sparqlToByteArray_r5mkub$ = function (buffer, value) {
    var tmp$ = value == null;
    if (!tmp$) {
      tmp$ = value.length === 0;
    }var tmp$_0 = tmp$;
    if (!tmp$_0) {
      tmp$_0 = equals(value.toLowerCase(), 'undef');
    }if (tmp$_0) {
      this.undefToByteArray_b1q5io$(buffer);
      return;
    }if (equals(value.toLowerCase(), 'error')) {
      this.errorToByteArray_b1q5io$(buffer);
      return;
    }if (equals(value.toLowerCase(), 'true')) {
      this.booleanToByteArray_5191p3$(buffer, true);
      return;
    }if (equals(value.toLowerCase(), 'false')) {
      this.booleanToByteArray_5191p3$(buffer, false);
      return;
    }if (startsWith(value, '_:')) {
      var endIndex = value.length;
      this.bnodeToByteArray_akwfwi$(buffer, value.substring(2, endIndex));
      return;
    }if (startsWith(value, '<') && endsWith_0(value, '>')) {
      var endIndex_0 = value.length - 1 | 0;
      this.iriToByteArray_akwfwi$(buffer, value.substring(1, endIndex_0));
      return;
    }if (!contains(value, 46)) {
      try {
        var i = BigInteger.Companion.parseString_bm4lxs$(value, 10);
        this.integerToByteArray_ddz2hi$(buffer, i);
        return;
      } catch (e) {
        if (!Kotlin.isType(e, Exception))
          throw e;
      }
    }if (!contains_0(value, 'e') && !contains_0(value, 'E')) {
      try {
        var d = BigDecimal.Companion.parseString_bm4lxs$(value, 10);
        this.decimalToByteArray_g73zp2$(buffer, d);
        return;
      } catch (e) {
        if (!Kotlin.isType(e, Exception))
          throw e;
      }
    }try {
      var d_0 = toDouble(value);
      this.doubleToByteArray_3eiwqq$(buffer, d_0);
      return;
    } catch (e) {
      if (!Kotlin.isType(e, Exception))
        throw e;
    }
    if (!endsWith_0(value, '' + String.fromCharCode(toBoxedChar(value.charCodeAt(0))))) {
      var typeIdx = lastIndexOf(value, '' + String.fromCharCode(toBoxedChar(value.charCodeAt(0))) + '^^<');
      var langIdx = lastIndexOf(value, '' + String.fromCharCode(toBoxedChar(value.charCodeAt(0))) + '@');
      if (endsWith_0(value, '>') && typeIdx > 0) {
        var endIndex_1 = typeIdx + 1 | 0;
        var tmp$_1 = this.removeQuotesFromString_61zpoe$(value.substring(0, endIndex_1));
        var startIndex = typeIdx + 4 | 0;
        var endIndex_2 = value.length - 1 | 0;
        this.typedToByteArray_v5q3o4$(buffer, tmp$_1, value.substring(startIndex, endIndex_2));
        return;
      } else {
        SanityCheckOn_getInstance().check_8i7tro$(DictionaryHelper$sparqlToByteArray$lambda(langIdx));
        var endIndex_3 = langIdx + 1 | 0;
        var tmp$_2 = this.removeQuotesFromString_61zpoe$(value.substring(0, endIndex_3));
        var startIndex_0 = langIdx + 2 | 0;
        var endIndex_4 = value.length;
        this.langToByteArray_v5q3o4$(buffer, tmp$_2, value.substring(startIndex_0, endIndex_4));
        return;
      }
    }this.stringToByteArray_akwfwi$(buffer, this.removeQuotesFromString_61zpoe$(value));
  };
  DictionaryHelper.prototype.removeQuotesFromString_61zpoe$ = function (s) {
    var c = s.charCodeAt(0);
    var cntLeft = 1;
    var cntRight = 0;
    if (c !== 39 && c !== 34 || c !== s.charCodeAt(s.length - 1 | 0)) {
      throw Exception_init('invalid quoted string >' + s + '<');
    }while (cntLeft < s.length && s.charCodeAt(cntLeft) === c) {
      cntLeft = cntLeft + 1 | 0;
    }
    while (cntRight < s.length && s.charCodeAt(s.length - cntRight - 1 | 0) === c) {
      cntRight = cntRight + 1 | 0;
    }
    if (cntLeft >= 3 && cntRight >= 3 && s.length >= 6) {
      var endIndex = s.length - 3 | 0;
      return s.substring(3, endIndex);
    }var endIndex_0 = s.length - 1 | 0;
    return s.substring(1, endIndex_0);
  };
  DictionaryHelper.prototype.valueDefinitionToByteArray_a55a6y$ = function (buffer, value) {
    this.sparqlToByteArray_r5mkub$(buffer, value.valueToString());
  };
  function DictionaryHelper$byteArrayToType$lambda(closure$res) {
    return function () {
      return closure$res >= 0;
    };
  }
  function DictionaryHelper$byteArrayToType$lambda_0(closure$res) {
    return function () {
      return closure$res.toString();
    };
  }
  function DictionaryHelper$byteArrayToType$lambda_1(closure$res) {
    return function () {
      return closure$res < 13;
    };
  }
  function DictionaryHelper$byteArrayToType$lambda_2(closure$res) {
    return function () {
      return closure$res.toString();
    };
  }
  DictionaryHelper.prototype.byteArrayToType_b1q5io$ = function (buffer) {
    var res = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, 0);
    SanityCheckOn_getInstance().check_a3x0x2$(DictionaryHelper$byteArrayToType$lambda(res), DictionaryHelper$byteArrayToType$lambda_0(res));
    SanityCheckOn_getInstance().check_a3x0x2$(DictionaryHelper$byteArrayToType$lambda_1(res), DictionaryHelper$byteArrayToType$lambda_2(res));
    return res;
  };
  DictionaryHelper.prototype.byteArrayToSparql_b1q5io$ = function (buffer) {
    var tmp$;
    var type = this.byteArrayToType_b1q5io$(buffer);
    switch (type) {
      case 12:
        tmp$ = 'UNDEF';
        break;
      case 5:
        tmp$ = 'ERROR';
        break;
      case 0:
        tmp$ = this.byteArrayToBnode_A_b1q5io$(buffer);
        break;
      case 1:
        if (this.byteArrayToBoolean_b1q5io$(buffer)) {
          tmp$ = '"true"^^<http://www.w3.org/2001/XMLSchema#boolean>';
        } else {
          tmp$ = '"false"^^<http://www.w3.org/2001/XMLSchema#boolean>';
        }

        break;
      case 4:
        tmp$ = '"' + this.byteArrayToDouble_S_b1q5io$(buffer) + '"^^<http://www.w3.org/2001/XMLSchema#double>';
        break;
      case 6:
        tmp$ = '"' + this.byteArrayToFloat_S_b1q5io$(buffer) + '"^^<http://www.w3.org/2001/XMLSchema#float>';
        break;
      case 7:
        tmp$ = '"' + this.byteArrayToInteger_S_b1q5io$(buffer) + '"^^<http://www.w3.org/2001/XMLSchema#integer>';
        break;
      case 3:
        tmp$ = '"' + this.byteArrayToDecimal_S_b1q5io$(buffer) + '"^^<http://www.w3.org/2001/XMLSchema#decimal>';
        break;
      case 8:
        tmp$ = '<' + this.byteArrayToIri_b1q5io$(buffer) + '>';
        break;
      case 9:
        tmp$ = '"' + this.byteArrayToString_b1q5io$(buffer) + '"';
        break;
      case 10:
        tmp$ = '"' + this.byteArrayToLang_Content_b1q5io$(buffer) + '"@' + this.byteArrayToLang_Lang_b1q5io$(buffer);
        break;
      case 11:
        tmp$ = '"' + this.byteArrayToTyped_Content_b1q5io$(buffer) + '"^^<' + this.byteArrayToTyped_Type_b1q5io$(buffer) + '>';
        break;
      case 2:
        tmp$ = '"' + this.byteArrayToDateTimeAsTyped_Content_b1q5io$(buffer) + '"^^<http://www.w3.org/2001/XMLSchema#dateTime>';
        break;
      default:throw Exception_init('unreachable ' + type);
    }
    return tmp$;
  };
  DictionaryHelper.prototype.byteArrayToValueDefinition_b1q5io$ = function (buffer) {
    var tmp$;
    var type = this.byteArrayToType_b1q5io$(buffer);
    switch (type) {
      case 12:
        tmp$ = dictionary.DictionaryExt.undefValue2;
        break;
      case 5:
        tmp$ = dictionary.DictionaryExt.errorValue2;
        break;
      case 0:
        tmp$ = new ValueBnode('' + toString(this.byteArrayToBnode_I_b1q5io$(buffer)));
        break;
      case 1:
        if (this.byteArrayToBoolean_b1q5io$(buffer)) {
          tmp$ = dictionary.DictionaryExt.booleanTrueValue2;
        } else {
          tmp$ = dictionary.DictionaryExt.booleanFalseValue2;
        }

        break;
      case 4:
        tmp$ = new ValueDouble(this.byteArrayToDouble_I_b1q5io$(buffer));
        break;
      case 6:
        tmp$ = new ValueFloat(this.byteArrayToFloat_I_b1q5io$(buffer));
        break;
      case 7:
        tmp$ = new ValueInteger(this.byteArrayToInteger_I_b1q5io$(buffer));
        break;
      case 3:
        tmp$ = new ValueDecimal(this.byteArrayToDecimal_I_b1q5io$(buffer));
        break;
      case 8:
        tmp$ = new ValueIri(this.byteArrayToIri_b1q5io$(buffer));
        break;
      case 9:
        tmp$ = new ValueSimpleLiteral('"', this.byteArrayToString_b1q5io$(buffer));
        break;
      case 10:
        tmp$ = new ValueLanguageTaggedLiteral('"', this.byteArrayToLang_Content_b1q5io$(buffer), this.byteArrayToLang_Lang_b1q5io$(buffer));
        break;
      case 11:
        tmp$ = ValueTypedLiteral.Companion.invoke_6hosri$('"', this.byteArrayToTyped_Content_b1q5io$(buffer), this.byteArrayToTyped_Type_b1q5io$(buffer));
        break;
      default:throw Exception_init('unreachable ' + type);
    }
    return tmp$;
  };
  DictionaryHelper.prototype.byteArrayToCallback_6o198z$ = function (buffer, onBNode, onBoolean, onLanguageTaggedLiteral, onSimpleLiteral, onTypedLiteral, onDecimal, onFloat, onDouble, onInteger, onIri, onError, onUndefined) {
    var type = ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer.buf, 0);
    switch (type) {
      case 6:
        onFloat(this.byteArrayToFloat_I_b1q5io$(buffer));
        break;
      case 4:
        onDouble(this.byteArrayToDouble_I_b1q5io$(buffer));
        break;
      case 7:
        onInteger(this.byteArrayToInteger_S_b1q5io$(buffer));
        break;
      case 3:
        onDecimal(this.byteArrayToDecimal_S_b1q5io$(buffer));
        break;
      case 12:
        onUndefined();
        break;
      case 5:
        onError();
        break;
      case 0:
        onBNode(this.byteArrayToBnode_I_b1q5io$(buffer));
        break;
      case 1:
        onBoolean(this.byteArrayToBoolean_b1q5io$(buffer));
        break;
      case 8:
        onIri(this.byteArrayToIri_b1q5io$(buffer));
        break;
      case 9:
        onSimpleLiteral(this.byteArrayToString_b1q5io$(buffer));
        break;
      case 10:
        onLanguageTaggedLiteral(this.byteArrayToLang_Content_b1q5io$(buffer), this.byteArrayToLang_Lang_b1q5io$(buffer));
        break;
      case 11:
        onTypedLiteral(this.byteArrayToTyped_Content_b1q5io$(buffer), this.byteArrayToTyped_Type_b1q5io$(buffer));
        break;
      case 2:
        onTypedLiteral(this.byteArrayToDateTimeAsTyped_Content_b1q5io$(buffer), 'http://www.w3.org/2001/XMLSchema#dateTime');
        break;
      default:throw Exception_init('unreachable ' + type);
    }
  };
  DictionaryHelper.prototype.byteArrayCompareAny_sllwic$ = function (a, b) {
    var typeA = this.byteArrayToType_b1q5io$(a);
    var typeB = this.byteArrayToType_b1q5io$(b);
    if (typeA !== typeB) {
      if (typeA === 12) {
        return -1;
      } else if (typeB === 12) {
        return 1;
      } else if (typeA === 5) {
        return -1;
      } else if (typeB === 5) {
        return 1;
      } else if (typeA === 0) {
        return -1;
      } else if (typeB === 0) {
        return 1;
      } else if (typeA === 8) {
        return -1;
      } else if (typeB === 8) {
        return 1;
      } else if (typeA === 9) {
        return -1;
      } else if (typeB === 9) {
        return 1;
      } else {
        return typeA - typeB | 0;
      }
    } else {
      if (typeA === 12 || typeA === 5) {
        return 0;
      } else if (typeA === 0) {
        if (a.size === 8 && b.size === 8) {
          return ByteArrayHelper_getInstance().readInt4_pao7sd$(a.buf, 4) - ByteArrayHelper_getInstance().readInt4_pao7sd$(b.buf, 4) | 0;
        } else {
          return a.compareTo_11rb$(b);
        }
      } else if (typeA === 1) {
        return a.buf[4] - b.buf[4];
      } else if (typeA !== 2)
        if (typeA !== 3)
          if (typeA !== 4)
            if (typeA !== 6)
              if (typeA !== 7)
                if (typeA === 10 || typeA === 11 || typeA === 8 || typeA === 9) {
                  var lenA = a.size;
                  var lenB = b.size;
                  var i = 4;
                  var res = 0;
                  while (i < lenA && i < lenB && res === 0) {
                    res = a.buf[i] - b.buf[i];
                    i = i + 1 | 0;
                  }
                  if (res === 0) {
                    res = lenA - lenB | 0;
                  }return res;
                }}
    throw Exception_init('can not compare ' + typeA + ' ' + typeB);
  };
  DictionaryHelper.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'DictionaryHelper',
    interfaces: []
  };
  var DictionaryHelper_instance = null;
  function DictionaryHelper_getInstance() {
    if (DictionaryHelper_instance === null) {
      new DictionaryHelper();
    }return DictionaryHelper_instance;
  }
  function ByteArrayWrapperExt() {
    ByteArrayWrapperExt_instance = this;
  }
  ByteArrayWrapperExt.prototype.setSize_v5fxe$ = function (data, c) {
    data.size = c;
    if (c > data.buf.length) {
      data.buf = new Int8Array(c);
    }};
  ByteArrayWrapperExt.prototype.setSizeCopy_v5fxe$ = function (data, c) {
    data.size = c;
    if (c > data.buf.length) {
      var oldBuf = data.buf;
      data.buf = new Int8Array(c);
      arrayCopy(oldBuf, data.buf, 0, 0, oldBuf.length);
    }};
  ByteArrayWrapperExt.prototype.commonBytes_sllwic$ = function (a, b) {
    var i = 0;
    while (i < a.size && i < b.size) {
      if (a.buf[i] === b.buf[i]) {
        i = i + 1 | 0;
      } else {
        break;
      }
    }
    return i;
  };
  ByteArrayWrapperExt.prototype.copyInto_sllwic$ = function (a, b) {
    this.setSize_v5fxe$(b, a.size);
    arrayCopy(a.buf, b.buf, 0, 0, a.size);
  };
  ByteArrayWrapperExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ByteArrayWrapperExt',
    interfaces: []
  };
  var ByteArrayWrapperExt_instance = null;
  function ByteArrayWrapperExt_getInstance() {
    if (ByteArrayWrapperExt_instance === null) {
      new ByteArrayWrapperExt();
    }return ByteArrayWrapperExt_instance;
  }
  function IntArrayWrapperExt() {
    IntArrayWrapperExt_instance = this;
  }
  IntArrayWrapperExt.prototype.setSize_b39gz4$ = function (data, c) {
    data.size = c;
    if (c > data.buf.length) {
      data.buf = new Int32Array(c);
    }};
  IntArrayWrapperExt.prototype.setSizeCopy_b39gz4$ = function (data, c) {
    data.size = c;
    if (c > data.buf.length) {
      var oldBuf = data.buf;
      data.buf = new Int32Array(c);
      arrayCopy(oldBuf, data.buf, 0, 0, oldBuf.length);
    }};
  IntArrayWrapperExt.prototype.copyInto_rs6nqr$ = function (a, b) {
    this.setSize_b39gz4$(b, a.size);
    arrayCopy(a.buf, b.buf, 0, 0, a.size);
  };
  IntArrayWrapperExt.prototype.append_b39gz4$ = function (data, v) {
    var tmp$;
    if (data.buf.length === data.size) {
      var oldBuf = data.buf;
      data.buf = new Int32Array(data.size * 2 | 0);
      arrayCopy(oldBuf, data.buf, 0, 0, oldBuf.length);
    }data.buf[tmp$ = data.size, data.size = tmp$ + 1 | 0, tmp$] = v;
  };
  IntArrayWrapperExt.prototype.removeLast_j4ucjm$ = function (data) {
    data.size = data.size - 1 | 0;
    return data.buf[data.size];
  };
  IntArrayWrapperExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'IntArrayWrapperExt',
    interfaces: []
  };
  var IntArrayWrapperExt_instance = null;
  function IntArrayWrapperExt_getInstance() {
    if (IntArrayWrapperExt_instance === null) {
      new IntArrayWrapperExt();
    }return IntArrayWrapperExt_instance;
  }
  function MyInputStreamFixedLength(stream, remainingBytes) {
    this.stream = stream;
    this.remainingBytes = remainingBytes;
  }
  MyInputStreamFixedLength.prototype.readInt = function () {
    if (this.remainingBytes >= 4) {
      this.remainingBytes = this.remainingBytes - 4 | 0;
      return this.stream.readInt();
    } else {
      throw Exception_init('not enough bytes available ' + this.remainingBytes);
    }
  };
  MyInputStreamFixedLength.prototype.readByte = function () {
    if (this.remainingBytes >= 1) {
      this.remainingBytes = this.remainingBytes - 1 | 0;
      return this.stream.readByte();
    } else {
      throw Exception_init('not enough bytes available ' + this.remainingBytes);
    }
  };
  MyInputStreamFixedLength.prototype.read_fqrh44$ = function (buf) {
    if (this.remainingBytes >= buf.length) {
      this.remainingBytes = this.remainingBytes - buf.length | 0;
      return this.stream.read_fqrh44$(buf);
    } else {
      throw Exception_init('not enough bytes available ' + this.remainingBytes);
    }
  };
  MyInputStreamFixedLength.prototype.read_ir89t6$ = function (buf, len) {
    if (this.remainingBytes >= len) {
      this.remainingBytes = this.remainingBytes - len | 0;
      return this.stream.read_ir89t6$(buf, len);
    } else {
      throw Exception_init('not enough bytes available ' + this.remainingBytes);
    }
  };
  MyInputStreamFixedLength.prototype.read_mj6st8$ = function (buf, off, len) {
    if (this.remainingBytes >= len) {
      this.remainingBytes = this.remainingBytes - len | 0;
      return this.stream.read_mj6st8$(buf, off, len);
    } else {
      throw Exception_init('not enough bytes available ' + this.remainingBytes);
    }
  };
  MyInputStreamFixedLength.prototype.close = function () {
    this.stream.close();
  };
  MyInputStreamFixedLength.prototype.readLine = function () {
    var buf = ArrayList_init_0();
    try {
      var b = this.readByte();
      while (b !== toByte(10 | 0)) {
        if (b !== toByte(13 | 0)) {
          buf.add_11rb$(b);
        }b = this.readByte();
      }
    } catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        printStackTrace(e);
        if (buf.size === 0) {
          return null;
        }} else
        throw e;
    }
    return decodeToString(toByteArray(buf));
  };
  MyInputStreamFixedLength.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MyInputStreamFixedLength',
    interfaces: [IMyInputStream]
  };
  function MyStringStream(str) {
    this.buf4 = new Int8Array(4);
    this.data = encodeToByteArray(str);
    this.pos = 0;
  }
  MyStringStream.prototype.close = function () {
  };
  MyStringStream.prototype.read_fqrh44$ = function (buf) {
    var s = this.pos + buf.length | 0;
    var res = buf.length;
    if (s > this.data.length) {
      s = this.data.length;
      res = s - this.pos | 0;
    }arrayCopy(this.data, buf, 0, this.pos, s);
    this.pos = s;
    return res;
  };
  MyStringStream.prototype.read_ir89t6$ = function (buf, len) {
    var s = this.pos + len | 0;
    var res = buf.length;
    if (s > this.data.length) {
      s = this.data.length;
      res = s - this.pos | 0;
    }arrayCopy(this.data, buf, 0, this.pos, s);
    this.pos = s;
    return res;
  };
  MyStringStream.prototype.read_mj6st8$ = function (buf, off, len) {
    var s = this.pos + len | 0;
    var res = buf.length;
    if (s > this.data.length) {
      s = this.data.length;
      res = s - this.pos | 0;
    }arrayCopy(this.data, buf, off, this.pos, s);
    this.pos = s;
    return res;
  };
  MyStringStream.prototype.readInt = function () {
    this.read_ir89t6$(this.buf4, 4);
    return ByteArrayHelper_getInstance().readInt4_pao7sd$(this.buf4, 0);
  };
  MyStringStream.prototype.readByte = function () {
    this.read_ir89t6$(this.buf4, 1);
    return this.buf4[0];
  };
  MyStringStream.prototype.readLine = function () {
    var buf = ArrayList_init_0();
    try {
      var b = this.readByte();
      while (b !== toByte(10 | 0)) {
        if (b !== toByte(13 | 0)) {
          buf.add_11rb$(b);
        }b = this.readByte();
      }
    } catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        printStackTrace(e);
        if (buf.size === 0) {
          return null;
        }} else
        throw e;
    }
    return decodeToString(toByteArray(buf));
  };
  MyStringStream.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MyStringStream',
    interfaces: [IMyInputStream]
  };
  function SanityCheckOff() {
    SanityCheckOff_instance = this;
  }
  SanityCheckOff.prototype.println_buffermanager_lh572t$ = function (s) {
  };
  SanityCheckOff.prototype.println_nodemanager_lh572t$ = function (s) {
  };
  SanityCheckOff.prototype.println_lh572t$ = function (s) {
  };
  SanityCheckOff.prototype.invoke_ls4sck$ = function (action) {
  };
  SanityCheckOff.prototype.suspended_ls4sck$ = function (action) {
  };
  SanityCheckOff.prototype.helper_lx1jwy$ = function (action) {
    return null;
  };
  SanityCheckOff.prototype.check_a3x0x2$ = function (value, msg) {
  };
  SanityCheckOff.prototype.check_8i7tro$ = function (value) {
  };
  SanityCheckOff.prototype.checkUnreachable_8be2vx$ = function () {
    throw new UnreachableException();
  };
  SanityCheckOff.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'SanityCheckOff',
    interfaces: []
  };
  var SanityCheckOff_instance = null;
  function SanityCheckOff_getInstance() {
    if (SanityCheckOff_instance === null) {
      new SanityCheckOff();
    }return SanityCheckOff_instance;
  }
  function SanityCheckOn() {
    SanityCheckOn_instance = this;
    this.SANITYCHECK_PRINTING_8be2vx$ = false;
    this.SANITYCHECK_PRINTING_NODEMANAGER_8be2vx$ = false;
    this.SANITYCHECK_PRINTING_BUFFERMANAGER_8be2vx$ = false;
  }
  SanityCheckOn.prototype.println_buffermanager_lh572t$ = function (s) {
    if (this.SANITYCHECK_PRINTING_BUFFERMANAGER_8be2vx$) {
      println(s());
    }};
  SanityCheckOn.prototype.println_nodemanager_lh572t$ = function (s) {
    if (this.SANITYCHECK_PRINTING_NODEMANAGER_8be2vx$) {
      println(s());
    }};
  SanityCheckOn.prototype.println_lh572t$ = function (s) {
    if (this.SANITYCHECK_PRINTING_8be2vx$) {
      println(s());
    }};
  SanityCheckOn.prototype.invoke_ls4sck$ = function (action) {
    try {
      action();
    } catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        if (this.SANITYCHECK_PRINTING_8be2vx$) {
          println('Exception during SanityCheck.invoke');
          printStackTrace(e);
        }throw e;
      } else
        throw e;
    }
  };
  SanityCheckOn.prototype.suspended_ls4sck$ = function (action) {
    try {
      action();
    } catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        if (this.SANITYCHECK_PRINTING_8be2vx$) {
          println('Exception during SanityCheck.suspended');
          printStackTrace(e);
        }throw e;
      } else
        throw e;
    }
  };
  SanityCheckOn.prototype.helper_i3ch5z$ = function (action) {
    return action();
  };
  SanityCheckOn.prototype.check_a3x0x2$ = function (value, msg) {
    try {
      if (!value()) {
        throw Exception_init('SanityCheck failed :: ' + msg());
      }} catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        if (this.SANITYCHECK_PRINTING_8be2vx$) {
          println('Exception during SanityCheck.check');
          printStackTrace(e);
        }throw e;
      } else
        throw e;
    }
  };
  SanityCheckOn.prototype.check_8i7tro$ = function (value) {
    try {
      if (!value()) {
        throw Exception_init('SanityCheck failed');
      }} catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        if (this.SANITYCHECK_PRINTING_8be2vx$) {
          println('Exception during SanityCheck.check');
          printStackTrace(e);
        }throw e;
      } else
        throw e;
    }
  };
  SanityCheckOn.prototype.checkUnreachable_8be2vx$ = function () {
    throw new UnreachableException();
  };
  SanityCheckOn.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'SanityCheckOn',
    interfaces: []
  };
  var SanityCheckOn_instance = null;
  function SanityCheckOn_getInstance() {
    if (SanityCheckOn_instance === null) {
      new SanityCheckOn();
    }return SanityCheckOn_instance;
  }
  function ByteArrayHelper() {
    ByteArrayHelper_instance = this;
  }
  ByteArrayHelper.prototype.readDouble8_pao7sd$ = function (data, offset) {
    var buffer = new ArrayBuffer(8);
    var intView = new Int64Array(buffer);
    var floatView = new Float64Array(buffer);
    intView.set(0, this.readLong8_pao7sd$(data, offset));
    return floatView.get(0);
  };
  ByteArrayHelper.prototype.writeDouble8_aunrlr$ = function (data, offset, value) {
    var buffer = new ArrayBuffer(8);
    var intView = new Int64Array(buffer);
    var floatView = new Float64Array(buffer);
    floatView.set(0, value);
    this.writeLong8_ul24ie$(data, offset, intView.get(0));
  };
  ByteArrayHelper.prototype.writeInt1_qibw1t$ = function (data, offset, value) {
    data[offset] = toByte(value & 255);
  };
  ByteArrayHelper.prototype.writeInt2_qibw1t$ = function (data, offset, value) {
    data[offset] = toByte(value >> 8 & 255);
    data[offset + 1 | 0] = toByte(value & 255);
  };
  ByteArrayHelper.prototype.writeInt3_qibw1t$ = function (data, offset, value) {
    data[offset] = toByte(value >> 16 & 255);
    data[offset + 1 | 0] = toByte(value >> 8 & 255);
    data[offset + 2 | 0] = toByte(value & 255);
  };
  ByteArrayHelper.prototype.writeInt4_qibw1t$ = function (data, offset, value) {
    data[offset] = toByte(value >> 24 & 255);
    data[offset + 1 | 0] = toByte(value >> 16 & 255);
    data[offset + 2 | 0] = toByte(value >> 8 & 255);
    data[offset + 3 | 0] = toByte(value & 255);
  };
  ByteArrayHelper.prototype.writeIntX_4f9ssz$ = function (data, offset, value, count) {
    switch (count) {
      case 0:
        break;
      case 1:
        this.writeInt1_qibw1t$(data, offset, value);
        break;
      case 2:
        this.writeInt2_qibw1t$(data, offset, value);
        break;
      case 3:
        this.writeInt3_qibw1t$(data, offset, value);
        break;
      default:this.writeInt4_qibw1t$(data, offset, value);
        break;
    }
  };
  ByteArrayHelper.prototype.writeLong8_ul24ie$ = function (data, offset, value) {
    data[offset] = toByte(value.shiftRight(56).and(L255).toInt());
    data[offset + 1 | 0] = toByte(value.shiftRight(48).and(L255).toInt());
    data[offset + 2 | 0] = toByte(value.shiftRight(40).and(L255).toInt());
    data[offset + 3 | 0] = toByte(value.shiftRight(32).and(L255).toInt());
    data[offset + 4 | 0] = toByte(value.shiftRight(24).and(L255).toInt());
    data[offset + 5 | 0] = toByte(value.shiftRight(16).and(L255).toInt());
    data[offset + 6 | 0] = toByte(value.shiftRight(8).and(L255).toInt());
    data[offset + 7 | 0] = toByte(value.and(L255).toInt());
  };
  ByteArrayHelper.prototype.writeChar_ul80vw$ = function (data, offset, value) {
    var v = value | 0;
    data[offset] = toByte(v >> 8 & 255);
    data[offset + 1 | 0] = toByte(v & 255);
  };
  ByteArrayHelper.prototype.readLong8_pao7sd$ = function (data, offset) {
    return Kotlin.Long.fromInt(data[offset]).and(L255).shiftLeft(56).or(Kotlin.Long.fromInt(data[offset + 1 | 0]).and(L255).shiftLeft(48)).or(Kotlin.Long.fromInt(data[offset + 2 | 0]).and(L255).shiftLeft(40)).or(Kotlin.Long.fromInt(data[offset + 3 | 0]).and(L255).shiftLeft(32)).or(Kotlin.Long.fromInt(data[offset + 4 | 0]).and(L255).shiftLeft(24)).or(Kotlin.Long.fromInt(data[offset + 5 | 0]).and(L255).shiftLeft(16)).or(Kotlin.Long.fromInt(data[offset + 6 | 0]).and(L255).shiftLeft(8)).or(Kotlin.Long.fromInt(data[offset + 7 | 0]).and(L255));
  };
  ByteArrayHelper.prototype.readInt4_pao7sd$ = function (data, offset) {
    return (data[offset] & 255) << 24 | (data[offset + 1 | 0] & 255) << 16 | (data[offset + 2 | 0] & 255) << 8 | data[offset + 3 | 0] & 255;
  };
  ByteArrayHelper.prototype.readInt3_pao7sd$ = function (data, offset) {
    return (data[offset] & 255) << 16 | (data[offset + 1 | 0] & 255) << 8 | data[offset + 2 | 0] & 255;
  };
  ByteArrayHelper.prototype.readInt2_pao7sd$ = function (data, offset) {
    return (data[offset] & 255) << 8 | data[offset + 1 | 0] & 255;
  };
  ByteArrayHelper.prototype.readInt1_pao7sd$ = function (data, offset) {
    return data[offset] & 255;
  };
  ByteArrayHelper.prototype.readIntX_qibw1t$ = function (data, offset, count) {
    switch (count) {
      case 0:
        return 0;
      case 1:
        return this.readInt1_pao7sd$(data, offset);
      case 2:
        return this.readInt2_pao7sd$(data, offset);
      case 3:
        return this.readInt3_pao7sd$(data, offset);
      default:return this.readInt4_pao7sd$(data, offset);
    }
  };
  ByteArrayHelper.prototype.readChar_pao7sd$ = function (data, offset) {
    return toChar((data[offset] & 255) << 8 | data[offset + 1 | 0] & 255);
  };
  ByteArrayHelper.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ByteArrayHelper',
    interfaces: []
  };
  var ByteArrayHelper_instance = null;
  function ByteArrayHelper_getInstance() {
    if (ByteArrayHelper_instance === null) {
      new ByteArrayHelper();
    }return ByteArrayHelper_instance;
  }
  function DateHelper() {
    this.time_8be2vx$ = new Date();
  }
  DateHelper.prototype.year_8be2vx$ = function () {
    return this.time_8be2vx$.getFullYear();
  };
  DateHelper.prototype.month_8be2vx$ = function () {
    return this.time_8be2vx$.getMonth();
  };
  DateHelper.prototype.day_8be2vx$ = function () {
    return this.time_8be2vx$.getDay();
  };
  DateHelper.prototype.hours_8be2vx$ = function () {
    return this.time_8be2vx$.getHours();
  };
  DateHelper.prototype.minutes_8be2vx$ = function () {
    return this.time_8be2vx$.getMinutes();
  };
  DateHelper.prototype.seconds_8be2vx$ = function () {
    return this.time_8be2vx$.getSeconds();
  };
  DateHelper.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'DateHelper',
    interfaces: []
  };
  function DateHelper_init($this) {
    $this = $this || Object.create(DateHelper.prototype);
    DateHelper.call($this);
    return $this;
  }
  function File() {
    this.filename_8be2vx$ = null;
  }
  File.prototype.createTempFile_p1hijf$ = function (prefix, suffix, directory) {
    throw new NotImplementedException('File', 'createTempFile not implemented');
  };
  File.prototype.exists_8be2vx$ = function () {
    throw new NotImplementedException('File', 'exists not implemented');
  };
  File.prototype.mkdirs_8be2vx$ = function () {
    throw new NotImplementedException('File', 'mkdirs not implemented');
  };
  File.prototype.deleteRecursively_8be2vx$ = function () {
    throw new NotImplementedException('File', 'deleteRecursively not implemented');
  };
  File.prototype.length_8be2vx$ = function () {
    throw new NotImplementedException('File', 'length not implemented');
  };
  function File$readAsString$lambda(closure$res) {
    return function (it) {
      closure$res.v.append_pdl1vj$(it).append_s8itvh$(10);
      return Unit;
    };
  }
  File.prototype.readAsString_8be2vx$ = function () {
    var res = {v: StringBuilder_init()};
    this.forEachLine_5y588g$(File$readAsString$lambda(res));
    return res.v.toString();
  };
  File.prototype.readAsCharIterator_8be2vx$ = function () {
    throw new NotImplementedException('File', 'readAsCharIterator not implemented');
  };
  File.prototype.openInputStream_8be2vx$ = function () {
    throw new NotImplementedException('File', 'openInputStream not implemented');
  };
  File.prototype.walk_5y588g$ = function (action) {
    throw new NotImplementedException('File', 'walk not implemented');
  };
  File.prototype.walk_4gst40$ = function (maxdepth, action) {
    throw new NotImplementedException('File', 'walk not implemented');
  };
  File.prototype.forEachLine_5y588g$ = function (action) {
    var stream = MyInputStream_init(this.filename_8be2vx$);
    var buffer = new Int8Array(8192);
    var pos = 0;
    var s = ArrayList_init_0();
    while (true) {
      var len = stream.read_ir89t6$(buffer, buffer.length);
      if (len === 0) {
        break;
      }for (var i = 0; i < len; i++) {
        var b = buffer[i];
        if (b === toByte(13) || b === toByte(10)) {
          action(decodeToString(toByteArray(s)));
          s.clear();
        } else {
          s.add_11rb$(b);
        }
      }
      pos = pos + len | 0;
    }
    action(decodeToString(toByteArray(s)));
    stream.close();
  };
  File.prototype.withOutputStream_2hu0ja$ = function (action) {
    throw new NotImplementedException('File', 'withOutputStream not implemented');
  };
  File.prototype.withInputStream_2c7cab$ = function (action) {
    var stream = MyInputStream_init(this.filename_8be2vx$);
    action(stream);
    stream.close();
  };
  File.prototype.equals = function (other) {
    throw new NotImplementedException('File', 'equals not implemented');
  };
  File.prototype.openOutputStream_vft4zs$ = function (append) {
    throw new NotImplementedException('File', 'openOutputStream not implemented');
  };
  File.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'File',
    interfaces: []
  };
  function File_init(filename, $this) {
    $this = $this || Object.create(File.prototype);
    File.call($this);
    $this.filename_8be2vx$ = filename;
    return $this;
  }
  function IntegerExt() {
    IntegerExt_instance = this;
  }
  IntegerExt.prototype.numberOfLeadingZeros_kcn2v3$ = function (value) {
    var i = 31;
    while (i >= 0) {
      if ((value & 1 << i) !== 0) {
        return 31 - i | 0;
      }i = i - 1 | 0;
    }
    return 32;
  };
  IntegerExt.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'IntegerExt',
    interfaces: []
  };
  var IntegerExt_instance = null;
  function IntegerExt_getInstance() {
    if (IntegerExt_instance === null) {
      new IntegerExt();
    }return IntegerExt_instance;
  }
  function MyInputStream() {
    this.fd_8be2vx$ = 0;
    this.pos_8be2vx$ = 0;
  }
  MyInputStream.prototype.readInt = function () {
    var buffer = new Int8Array(4);
    var l = fs.ExternalModule_fs.readSync_ir43ts$(this.fd_8be2vx$, buffer, 0, buffer.length, this.pos_8be2vx$);
    if (l !== 4) {
      throw Exception_init('invalid len ' + l);
    }this.pos_8be2vx$ = this.pos_8be2vx$ + l | 0;
    return ByteArrayHelper_getInstance().readInt4_pao7sd$(buffer, 0);
  };
  MyInputStream.prototype.readByte = function () {
    var buffer = new Int8Array(1);
    var l = fs.ExternalModule_fs.readSync_ir43ts$(this.fd_8be2vx$, buffer, 0, buffer.length, this.pos_8be2vx$);
    if (l !== 1) {
      throw Exception_init('invalid len ' + l);
    }this.pos_8be2vx$ = this.pos_8be2vx$ + l | 0;
    return buffer[0];
  };
  MyInputStream.prototype.read_mj6st8$ = function (buf, off, len) {
    var l = fs.ExternalModule_fs.readSync_ir43ts$(this.fd_8be2vx$, buf, off, len, this.pos_8be2vx$);
    this.pos_8be2vx$ = this.pos_8be2vx$ + l | 0;
    return l;
  };
  MyInputStream.prototype.read_ir89t6$ = function (buf, len) {
    var off = 0;
    var l = len;
    while (l > 0) {
      var tmp = fs.ExternalModule_fs.readSync_ir43ts$(this.fd_8be2vx$, buf, off, len, this.pos_8be2vx$);
      if (tmp <= 0) {
        return len - l | 0;
      }l = l - len | 0;
      off = off + len | 0;
      this.pos_8be2vx$ = this.pos_8be2vx$ + tmp | 0;
    }
    return len;
  };
  MyInputStream.prototype.read_fqrh44$ = function (buf) {
    return this.read_ir89t6$(buf, buf.length);
  };
  MyInputStream.prototype.close = function () {
    fs.ExternalModule_fs.closeSync_za3lpa$(this.fd_8be2vx$);
  };
  MyInputStream.prototype.readLine = function () {
    var buf = ArrayList_init_0();
    try {
      var b = this.readByte();
      while (b !== toByte(10 | 0)) {
        if (b !== toByte(13 | 0)) {
          buf.add_11rb$(b);
        }b = this.readByte();
      }
    } catch (e) {
      if (Kotlin.isType(e, Throwable)) {
        printStackTrace(e);
        if (buf.size === 0) {
          return null;
        }} else
        throw e;
    }
    return decodeToString(toByteArray(buf));
  };
  MyInputStream.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MyInputStream',
    interfaces: [IMyInputStream]
  };
  function MyInputStream_init(filename, $this) {
    $this = $this || Object.create(MyInputStream.prototype);
    MyInputStream.call($this);
    $this.fd_8be2vx$ = fs.ExternalModule_fs.openSync_puj7f4$(filename, 'r');
    return $this;
  }
  function MyInputStream_init_0(fd, $this) {
    $this = $this || Object.create(MyInputStream.prototype);
    MyInputStream.call($this);
    $this.fd_8be2vx$ = fd;
    return $this;
  }
  function MyOutputStream() {
  }
  MyOutputStream.prototype.writeInt_za3lpa$ = function (value) {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  MyOutputStream.prototype.close = function () {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  MyOutputStream.prototype.flush = function () {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  MyOutputStream.prototype.write_fqrh44$ = function (buf) {
    this.write_ir89t6$(buf, buf.length);
  };
  MyOutputStream.prototype.write_ir89t6$ = function (buf, len) {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  MyOutputStream.prototype.println_61zpoe$ = function (x) {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  MyOutputStream.prototype.print_61zpoe$ = function (x) {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  MyOutputStream.prototype.print_6taknv$ = function (x) {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  MyOutputStream.prototype.print_za3lpa$ = function (x) {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  MyOutputStream.prototype.print_14dthe$ = function (x) {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  MyOutputStream.prototype.println = function () {
    throw new NotImplementedException('MyOutputStream', 'xyz not implemented');
  };
  MyOutputStream.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MyOutputStream',
    interfaces: [IMyOutputStream]
  };
  function MyOutputStream_init($this) {
    $this = $this || Object.create(MyOutputStream.prototype);
    MyOutputStream.call($this);
    return $this;
  }
  function MyPrintWriter() {
    this.buffer_8be2vx$ = StringBuilder_init();
    this.bufferMode_8be2vx$ = 0;
    this.fileName_8be2vx$ = null;
    this.file_8be2vx$ = 0;
    this.filePos_8be2vx$ = 0;
  }
  MyPrintWriter.prototype.clearBuffer = function () {
    if (this.bufferMode_8be2vx$ === 0) {
      this.buffer_8be2vx$.clear();
    } else {
      throw Exception_init('not supported');
    }
  };
  MyPrintWriter.prototype.toString = function () {
    if (this.bufferMode_8be2vx$ === 0) {
      return this.buffer_8be2vx$.toString();
    } else {
      throw Exception_init('not supported');
    }
  };
  MyPrintWriter.prototype.println_61zpoe$ = function (x) {
    if (this.bufferMode_8be2vx$ !== 1) {
      this.buffer_8be2vx$.append_pdl1vj$(x).append_s8itvh$(10);
    }};
  MyPrintWriter.prototype.print_61zpoe$ = function (x) {
    if (this.bufferMode_8be2vx$ !== 1) {
      this.buffer_8be2vx$.append_pdl1vj$(x);
    }};
  MyPrintWriter.prototype.println_6taknv$ = function (x) {
    if (this.bufferMode_8be2vx$ !== 1) {
      this.buffer_8be2vx$.append_6taknv$(x).append_s8itvh$(10);
    }};
  MyPrintWriter.prototype.print_6taknv$ = function (x) {
    if (this.bufferMode_8be2vx$ !== 1) {
      this.buffer_8be2vx$.append_6taknv$(x);
    }};
  MyPrintWriter.prototype.println_za3lpa$ = function (x) {
    if (this.bufferMode_8be2vx$ !== 1) {
      this.buffer_8be2vx$.append_s8jyv4$(x).append_s8itvh$(10);
    }};
  MyPrintWriter.prototype.print_za3lpa$ = function (x) {
    if (this.bufferMode_8be2vx$ !== 1) {
      this.buffer_8be2vx$.append_s8jyv4$(x);
    }};
  MyPrintWriter.prototype.println_14dthe$ = function (x) {
    if (this.bufferMode_8be2vx$ !== 1) {
      this.buffer_8be2vx$.append_s8jyv4$(x).append_s8itvh$(10);
    }};
  MyPrintWriter.prototype.print_14dthe$ = function (x) {
    if (this.bufferMode_8be2vx$ !== 1) {
      this.buffer_8be2vx$.append_s8jyv4$(x);
    }};
  MyPrintWriter.prototype.println = function () {
    if (this.bufferMode_8be2vx$ !== 1) {
      this.buffer_8be2vx$.append_s8itvh$(10);
    }};
  MyPrintWriter.prototype.write_ir89t6$ = function (buf, len) {
    throw Exception_init('not supported');
  };
  MyPrintWriter.prototype.write_fqrh44$ = function (buf) {
    throw Exception_init('not supported');
  };
  MyPrintWriter.prototype.writeInt_za3lpa$ = function (value) {
    throw Exception_init('not supported');
  };
  MyPrintWriter.prototype.close = function () {
    throw Exception_init('not supported');
  };
  MyPrintWriter.prototype.flush = function () {
    throw Exception_init('not supported');
  };
  MyPrintWriter.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MyPrintWriter',
    interfaces: [IMyOutputStream]
  };
  function MyPrintWriter_init(hasBuffer, $this) {
    if (hasBuffer === void 0)
      hasBuffer = true;
    $this = $this || Object.create(MyPrintWriter.prototype);
    MyPrintWriter.call($this);
    if (hasBuffer) {
      $this.bufferMode_8be2vx$ = 0;
    } else {
      $this.bufferMode_8be2vx$ = 1;
    }
    $this.fileName_8be2vx$ = '';
    $this.file_8be2vx$ = -1;
    return $this;
  }
  function MyThreadReadWriteLock() {
    this.uuid_8be2vx$ = shared.UUID_Counter.getNextUUID();
    this.lockedRead_8be2vx$ = 0;
    this.lockedWrite_8be2vx$ = false;
  }
  MyThreadReadWriteLock.prototype.getUUID_8be2vx$ = function () {
    return this.uuid_8be2vx$;
  };
  function MyThreadReadWriteLock$downgradeToReadLock$lambda(this$MyThreadReadWriteLock) {
    return function () {
      if (!this$MyThreadReadWriteLock.lockedWrite_8be2vx$) {
        throw Exception_init('something went wrong 1');
      }this$MyThreadReadWriteLock.lockedRead_8be2vx$ = 1;
      this$MyThreadReadWriteLock.lockedWrite_8be2vx$ = false;
      return Unit;
    };
  }
  MyThreadReadWriteLock.prototype.downgradeToReadLock_8be2vx$ = function () {
    SanityCheckOn_getInstance().invoke_ls4sck$(MyThreadReadWriteLock$downgradeToReadLock$lambda(this));
  };
  function MyThreadReadWriteLock$readLock$lambda(this$MyThreadReadWriteLock) {
    return function () {
      var tmp$;
      if (this$MyThreadReadWriteLock.lockedWrite_8be2vx$) {
        throw Exception_init('something went wrong 2');
      }tmp$ = this$MyThreadReadWriteLock.lockedRead_8be2vx$;
      this$MyThreadReadWriteLock.lockedRead_8be2vx$ = tmp$ + 1 | 0;
      return Unit;
    };
  }
  MyThreadReadWriteLock.prototype.readLock_8be2vx$ = function () {
    SanityCheckOn_getInstance().invoke_ls4sck$(MyThreadReadWriteLock$readLock$lambda(this));
  };
  function MyThreadReadWriteLock$readUnlock$lambda(this$MyThreadReadWriteLock) {
    return function () {
      var tmp$;
      if (this$MyThreadReadWriteLock.lockedRead_8be2vx$ <= 0) {
        throw Exception_init('something went wrong 3');
      }tmp$ = this$MyThreadReadWriteLock.lockedRead_8be2vx$;
      this$MyThreadReadWriteLock.lockedRead_8be2vx$ = tmp$ - 1 | 0;
      return Unit;
    };
  }
  MyThreadReadWriteLock.prototype.readUnlock_8be2vx$ = function () {
    SanityCheckOn_getInstance().invoke_ls4sck$(MyThreadReadWriteLock$readUnlock$lambda(this));
  };
  function MyThreadReadWriteLock$writeLock$lambda(this$MyThreadReadWriteLock) {
    return function () {
      if (this$MyThreadReadWriteLock.lockedRead_8be2vx$ > 0 || this$MyThreadReadWriteLock.lockedWrite_8be2vx$) {
        throw Exception_init('something went wrong 4 ' + this$MyThreadReadWriteLock.lockedRead_8be2vx$ + ' ' + this$MyThreadReadWriteLock.lockedWrite_8be2vx$);
      }this$MyThreadReadWriteLock.lockedWrite_8be2vx$ = true;
      return Unit;
    };
  }
  MyThreadReadWriteLock.prototype.writeLock_8be2vx$ = function () {
    SanityCheckOn_getInstance().invoke_ls4sck$(MyThreadReadWriteLock$writeLock$lambda(this));
  };
  function MyThreadReadWriteLock$tryWriteLock$lambda(this$MyThreadReadWriteLock) {
    return function () {
      if (this$MyThreadReadWriteLock.lockedRead_8be2vx$ > 0 || this$MyThreadReadWriteLock.lockedWrite_8be2vx$) {
        throw Exception_init('something went wrong 5 ' + this$MyThreadReadWriteLock.lockedRead_8be2vx$ + ' ' + this$MyThreadReadWriteLock.lockedWrite_8be2vx$);
      }this$MyThreadReadWriteLock.lockedWrite_8be2vx$ = true;
      return Unit;
    };
  }
  MyThreadReadWriteLock.prototype.tryWriteLock_8be2vx$ = function () {
    SanityCheckOn_getInstance().invoke_ls4sck$(MyThreadReadWriteLock$tryWriteLock$lambda(this));
    return true;
  };
  function MyThreadReadWriteLock$writeUnlock$lambda(this$MyThreadReadWriteLock) {
    return function () {
      if (!this$MyThreadReadWriteLock.lockedWrite_8be2vx$) {
        throw Exception_init('something went wrong 6');
      }this$MyThreadReadWriteLock.lockedWrite_8be2vx$ = false;
      return Unit;
    };
  }
  MyThreadReadWriteLock.prototype.writeUnlock_8be2vx$ = function () {
    SanityCheckOn_getInstance().invoke_ls4sck$(MyThreadReadWriteLock$writeUnlock$lambda(this));
  };
  MyThreadReadWriteLock.prototype.withReadLock_i3ch5z$ = function (action) {
    this.readLock_8be2vx$();
    try {
      return action();
    }finally {
      this.readUnlock_8be2vx$();
    }
  };
  MyThreadReadWriteLock.prototype.withWriteLock_i3ch5z$ = function (action) {
    this.writeLock_8be2vx$();
    try {
      return action();
    }finally {
      this.writeUnlock_8be2vx$();
    }
  };
  MyThreadReadWriteLock.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'MyThreadReadWriteLock',
    interfaces: []
  };
  function ParallelThread() {
    ParallelThread_instance = this;
  }
  ParallelThread.prototype.runBlocking_i3ch5z$ = function (action) {
    return action();
  };
  ParallelThread.prototype.launch_ls4sck$ = function (action) {
    throw new NotImplementedException('ParallelThread', 'launch not implemented');
  };
  ParallelThread.prototype.delay_8e33dg$ = function (milliseconds) {
  };
  ParallelThread.prototype.createCondition_8be2vx$ = function () {
    return new ParallelThreadCondition();
  };
  ParallelThread.prototype.createQueue_41v7ql$ = function (terminationValue) {
    return ParallelThreadQueue_init(terminationValue);
  };
  ParallelThread.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ParallelThread',
    interfaces: []
  };
  var ParallelThread_instance = null;
  function ParallelThread_getInstance() {
    if (ParallelThread_instance === null) {
      new ParallelThread();
    }return ParallelThread_instance;
  }
  function ParallelThreadCondition() {
  }
  ParallelThreadCondition.prototype.waitCondition_8i7tro$ = function (condition) {
    throw new NotImplementedException('ParallelThreadCondition', 'waitCondition not implemented');
  };
  ParallelThreadCondition.prototype.signal_8be2vx$ = function () {
    throw new NotImplementedException('ParallelThreadCondition', 'signal not implemented');
  };
  ParallelThreadCondition.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ParallelThreadCondition',
    interfaces: []
  };
  function ParallelThreadQueue() {
    this.queue = ArrayList_init_0();
    this.terminalValue = null;
  }
  ParallelThreadQueue.prototype.send_1c3m6u$ = function (value) {
    this.queue.add_11rb$(value);
  };
  ParallelThreadQueue.prototype.close_8be2vx$ = function () {
    this.queue.clear();
  };
  ParallelThreadQueue.prototype.receive_8be2vx$ = function () {
    if (this.queue.size > 0) {
      return this.queue.removeAt_za3lpa$(0);
    }return this.terminalValue;
  };
  ParallelThreadQueue.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'ParallelThreadQueue',
    interfaces: []
  };
  function ParallelThreadQueue_init(terminationValue, $this) {
    $this = $this || Object.create(ParallelThreadQueue.prototype);
    ParallelThreadQueue.call($this);
    $this.terminalValue = terminationValue;
    return $this;
  }
  function Platform() {
    Platform_instance = this;
    this.operatingSystem = 0;
  }
  Platform.prototype.getHostName_8be2vx$ = function () {
    throw Exception_init('not available on this platform');
  };
  Platform.prototype.getOperatingSystem_8be2vx$ = function () {
    return this.operatingSystem;
  };
  Platform.prototype.getUserHome_8be2vx$ = function () {
    throw Exception_init('not available on this platform');
  };
  Platform.prototype.getPathSeparator_8be2vx$ = function () {
    throw Exception_init('not available on this platform');
  };
  Platform.prototype.findNamedFileInDirectory_wdz5eb$ = function (dir, name) {
    throw Exception_init('not available on this platform');
  };
  Platform.prototype.getNullFileName_8be2vx$ = function () {
    throw Exception_init('not available on this platform');
  };
  Platform.prototype.getEnv_9lovpo$ = function (key, default_0) {
    if (default_0 === void 0)
      default_0 = null;
    return default_0;
  };
  Platform.prototype.getGradleCache_8be2vx$ = function () {
    return ensureNotNull(this.getEnv_9lovpo$('LUPOS_GRADLE_CACHE', this.getUserHome_8be2vx$() + this.getPathSeparator_8be2vx$() + '.gradle' + this.getPathSeparator_8be2vx$() + 'caches' + this.getPathSeparator_8be2vx$()));
  };
  Platform.prototype.getMavenCache_8be2vx$ = function () {
    return ensureNotNull(this.getEnv_9lovpo$('LUPOS_MAVEN_CACHE', this.getUserHome_8be2vx$() + this.getPathSeparator_8be2vx$() + '.m2' + this.getPathSeparator_8be2vx$() + 'repository' + this.getPathSeparator_8be2vx$()));
  };
  Platform.prototype.getAvailableRam_8be2vx$ = function () {
    return toInt(ensureNotNull(this.getEnv_9lovpo$('LUPOS_RAM', '4')));
  };
  Platform.prototype.setShutdownHock_ls4sck$ = function (action) {
    println('registering shutdown hook not implemented');
  };
  Platform.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'Platform',
    interfaces: []
  };
  var Platform_instance = null;
  function Platform_getInstance() {
    if (Platform_instance === null) {
      new Platform();
    }return Platform_instance;
  }
  var package$lupos = _.lupos || (_.lupos = {});
  var package$optimizer = package$lupos.optimizer || (package$lupos.optimizer = {});
  var package$physical = package$optimizer.physical || (package$optimizer.physical = {});
  package$physical.PhysicalOptimizer = PhysicalOptimizer;
  package$physical.PhysicalOptimizerDebug = PhysicalOptimizerDebug;
  package$physical.PhysicalOptimizerJoinType = PhysicalOptimizerJoinType;
  package$physical.PhysicalOptimizerNaive = PhysicalOptimizerNaive;
  package$physical.PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperator = PhysicalOptimizerPartitionAssignsSamePartitionCountToAnyRelatedOperator;
  package$physical.PhysicalOptimizerPartitionAssingPartitionsToRemaining = PhysicalOptimizerPartitionAssingPartitionsToRemaining;
  package$physical.PhysicalOptimizerPartitionExpandPartitionTowardsStore = PhysicalOptimizerPartitionExpandPartitionTowardsStore;
  package$physical.PhysicalOptimizerPartitionExpandTowardsRoot = PhysicalOptimizerPartitionExpandTowardsRoot;
  package$physical.PhysicalOptimizerPartitionRemoveUselessPartitions = PhysicalOptimizerPartitionRemoveUselessPartitions;
  package$physical.PhysicalOptimizerPartitionRespectMaxPartitions = PhysicalOptimizerPartitionRespectMaxPartitions;
  package$physical.PhysicalOptimizerTripleIndex = PhysicalOptimizerTripleIndex;
  package$physical.PhysicalOptimizerVisualisation = PhysicalOptimizerVisualisation;
  var package$Luposdate3000_Optimizer_Physical = package$lupos.Luposdate3000_Optimizer_Physical || (package$lupos.Luposdate3000_Optimizer_Physical = {});
  Object.defineProperty(package$Luposdate3000_Optimizer_Physical, 'BufferManagerPage', {
    get: BufferManagerPage_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Optimizer_Physical, 'ColumnIteratorQueueExt', {
    get: ColumnIteratorQueueExt_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Optimizer_Physical, 'DictionaryHelper', {
    get: DictionaryHelper_getInstance
  });
  var package$dynamicArray = package$Luposdate3000_Optimizer_Physical.dynamicArray || (package$Luposdate3000_Optimizer_Physical.dynamicArray = {});
  Object.defineProperty(package$dynamicArray, 'ByteArrayWrapperExt', {
    get: ByteArrayWrapperExt_getInstance
  });
  Object.defineProperty(package$dynamicArray, 'IntArrayWrapperExt', {
    get: IntArrayWrapperExt_getInstance
  });
  package$Luposdate3000_Optimizer_Physical.MyInputStreamFixedLength = MyInputStreamFixedLength;
  package$Luposdate3000_Optimizer_Physical.MyStringStream = MyStringStream;
  Object.defineProperty(package$Luposdate3000_Optimizer_Physical, 'SanityCheckOff', {
    get: SanityCheckOff_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Optimizer_Physical, 'SanityCheckOn', {
    get: SanityCheckOn_getInstance
  });
  Object.defineProperty(package$Luposdate3000_Optimizer_Physical, 'ByteArrayHelper', {
    get: ByteArrayHelper_getInstance
  });
  package$Luposdate3000_Optimizer_Physical.DateHelper_init = DateHelper_init;
  package$Luposdate3000_Optimizer_Physical.DateHelper = DateHelper;
  package$Luposdate3000_Optimizer_Physical.File_init_61zpoe$ = File_init;
  package$Luposdate3000_Optimizer_Physical.File = File;
  Object.defineProperty(package$Luposdate3000_Optimizer_Physical, 'IntegerExt', {
    get: IntegerExt_getInstance
  });
  package$Luposdate3000_Optimizer_Physical.MyInputStream_init_y4putb$ = MyInputStream_init;
  package$Luposdate3000_Optimizer_Physical.MyInputStream_init_kcn2v3$ = MyInputStream_init_0;
  package$Luposdate3000_Optimizer_Physical.MyInputStream = MyInputStream;
  package$Luposdate3000_Optimizer_Physical.MyOutputStream_init_8be2vx$ = MyOutputStream_init;
  package$Luposdate3000_Optimizer_Physical.MyOutputStream = MyOutputStream;
  package$Luposdate3000_Optimizer_Physical.MyPrintWriter_init_6taknv$ = MyPrintWriter_init;
  package$Luposdate3000_Optimizer_Physical.MyPrintWriter = MyPrintWriter;
  package$Luposdate3000_Optimizer_Physical.MyThreadReadWriteLock = MyThreadReadWriteLock;
  Object.defineProperty(package$Luposdate3000_Optimizer_Physical, 'ParallelThread', {
    get: ParallelThread_getInstance
  });
  package$Luposdate3000_Optimizer_Physical.ParallelThreadCondition = ParallelThreadCondition;
  package$Luposdate3000_Optimizer_Physical.ParallelThreadQueue_init_mh5how$ = ParallelThreadQueue_init;
  package$Luposdate3000_Optimizer_Physical.ParallelThreadQueue = ParallelThreadQueue;
  Object.defineProperty(package$Luposdate3000_Optimizer_Physical, 'Platform', {
    get: Platform_getInstance
  });
  Kotlin.defineModule('Luposdate3000_Optimizer_Physical', _);
  return _;
}));

//# sourceMappingURL=Luposdate3000_Optimizer_Physical.js.map
