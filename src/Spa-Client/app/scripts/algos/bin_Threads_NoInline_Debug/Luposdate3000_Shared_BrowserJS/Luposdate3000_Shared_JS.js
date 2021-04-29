(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Shared_JS'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Luposdate3000_Shared_JS'.");
    }root.Luposdate3000_Shared_JS = factory(typeof Luposdate3000_Shared_JS === 'undefined' ? {} : Luposdate3000_Shared_JS, kotlin);
  }
}(this, function (_, Kotlin) {
  'use strict';
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
  var Exception_init = Kotlin.kotlin.Exception_init_pdl1vj$;
  function ExternalModule_fs() {
    ExternalModule_fs_instance = this;
  }
  ExternalModule_fs.prototype.openSync_puj7f4$ = function (filename, flags) {
    return openSync_(filename, flags);
  };
  ExternalModule_fs.prototype.readSync_ir43ts$ = function (fd, buffer, offset, length, position) {
    return readSync_(fd, buffer, offset, length, position);
  };
  ExternalModule_fs.prototype.writeSync_ir43ts$ = function (fd, buffer, offset, length, position) {
    return writeSync_(fd, buffer, offset, length, position);
  };
  ExternalModule_fs.prototype.closeSync_za3lpa$ = function (fd) {
    closeSync_(fd);
  };
  ExternalModule_fs.prototype.readFileSync_61zpoe$ = function (filename) {
    return readFileSync_(filename);
  };
  ExternalModule_fs.$metadata$ = {
    kind: Kind_OBJECT,
    simpleName: 'ExternalModule_fs',
    interfaces: []
  };
  var ExternalModule_fs_instance = null;
  function ExternalModule_fs_getInstance() {
    if (ExternalModule_fs_instance === null) {
      new ExternalModule_fs();
    }return ExternalModule_fs_instance;
  }
  function openSync_(filename, flags) {
    throw Exception_init('not implemented');
  }
  function readSync_(fd, buffer, offset, length, position) {
    throw Exception_init('not implemented');
  }
  function writeSync_(fd, buffer, offset, length, position) {
    throw Exception_init('not implemented');
  }
  function closeSync_(fd) {
    throw Exception_init('not implemented');
  }
  function readFileSync_(filename) {
    throw Exception_init('not implemented');
  }
  var package$ext = _.ext || (_.ext = {});
  var package$fs = package$ext.fs || (package$ext.fs = {});
  Object.defineProperty(package$fs, 'ExternalModule_fs', {
    get: ExternalModule_fs_getInstance
  });
  package$fs.openSync = openSync_;
  package$fs.readSync = readSync_;
  package$fs.writeSync = writeSync_;
  package$fs.closeSync = closeSync_;
  package$fs.readFileSync = readFileSync_;
  Kotlin.defineModule('Luposdate3000_Shared_JS', _);
  return _;
}));

//# sourceMappingURL=Luposdate3000_Shared_JS.js.map
