(function (root, factory) {
  if (typeof define === 'function' && define.amd)
    define(['exports', 'kotlin', 'fs'], factory);
  else if (typeof exports === 'object')
    factory(module.exports, require('kotlin'), require('fs'));
  else {
    if (typeof kotlin === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Shared_JS'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Luposdate3000_Shared_JS'.");
    }if (typeof fs === 'undefined') {
      throw new Error("Error loading module 'Luposdate3000_Shared_JS'. Its dependency 'fs' was not found. Please, check whether 'fs' is loaded prior to 'Luposdate3000_Shared_JS'.");
    }root.Luposdate3000_Shared_JS = factory(typeof Luposdate3000_Shared_JS === 'undefined' ? {} : Luposdate3000_Shared_JS, kotlin, fs);
  }
}(this, function (_, Kotlin, $module$fs) {
  'use strict';
  var openSync_ = $module$fs.openSync;
  var readSync_ = $module$fs.readSync;
  var writeSync_ = $module$fs.writeSync;
  var closeSync_ = $module$fs.closeSync;
  var readFileSync_ = $module$fs.readFileSync;
  var Kind_OBJECT = Kotlin.Kind.OBJECT;
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
  var package$ext = _.ext || (_.ext = {});
  var package$fs = package$ext.fs || (package$ext.fs = {});
  Object.defineProperty(package$fs, 'ExternalModule_fs', {
    get: ExternalModule_fs_getInstance
  });
  Kotlin.defineModule('Luposdate3000_Shared_JS', _);
  return _;
}));

//# sourceMappingURL=Luposdate3000_Shared_JS.js.map
