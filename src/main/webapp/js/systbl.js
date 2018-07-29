(function() {
  var rcdStore;
  $c.checkAndCreate("$w");
  $w.handleChange = function(jsx, e) {
    return $c.handleChange(jsx, e.target.name, e.target.value);
  };
  $w.handleClick = function(jsx, e) {
    var ids, name, selRow, temp;
    name = e.target.name;
    if (name === "alert#CloseBtn") {
      $w.flux.actions.$c_alertHide();
    }
    if (name === "deleteCfm#CloseBtn") {
      $w.flux.actions.$c_deleteCfmHide();
    }
    if (name === "deleteCfm#YesBtn") {
      $w.flux.actions.$c_deleteCfmHide();
      $w.formDeleteCfm(jsx);
    }
    if (name === "btnNew") {
      $w.formClear(jsx);
    }
    if (name === "btnSearch") {
      $w.formSearch(jsx);
    }
    if (name === "btnUpdate") {
      $w.formUpdate(jsx);
    }
    if (name === "btnDelete") {
      $w.formDelete(jsx);
    }
    if (typeof e.target.id === "undefined") {
      return;
    }
    ids = e.target.id.split("#");
    if (ids[0] === "row") {
      temp = {
        systbl: jsx.state.systbl
      };
      selRow = Number(ids[2]);
      temp.systbl.selRow = selRow;
      temp.form = _.cloneDeep(temp.systbl.rcds[selRow]);
      temp.form.password = "";
      temp.form.passwordcfm = "";
      return jsx.setState(temp);
    }
  };
  $w.formSearch = function(jsx) {
    var criteria;
    criteria = $c.createCriteria(jsx.state.search, ["tableName", "key1"]);
    return $w.flux.actions.$c_rcd_fetch(jsx.state.systbl, jsx.state.form, "systbl", criteria);
  };
  $w.formUpdate = function(jsx) {
    var form, res, rules;
    form = jsx.state.form;
    res = "";
    if (form.id === "") {
      rules = [];
    }
    if (res.length > 0) {
      $w.flux.actions.$c_alertShow(res);
      return;
    }
    return $w.flux.actions.$c_rcd_update(jsx.state.systbl, jsx.state.form, "systbl");
  };
  $w.formDelete = function(jsx) {
    if (jsx.state.form.id === "") {
      $w.flux.actions.$c_rcd_delete_id_blank();
      return;
    }
    return $w.flux.actions.$c_deleteCfmShow();
  };
  $w.formDeleteCfm = function(jsx) {
    return $w.flux.actions.$c_rcd_delete(jsx.state.systbl, jsx.state.form, "systbl");
  };
  $w.formUpdateCheck = function(form) {
    if (form.password > "" || form.passwordcfm > "") {
      if (form.password !== form.passwordcfm) {
        return [["", "パスワードとパスワード（確認）が一致しません"]];
      }
    }
    return "";
  };
  $w.formClear = function(jsx) {
    var formtemp;
    formtemp = {
      form: _.cloneDeep(jsx.state.systbl.blank)
    };
    return jsx.setState(formtemp);
  };
  $w.constants = {
    $W_LOGIN_SUCCESS: "$W_LOGIN_SUCCESS"
  };
  $w.actions = {
    logoffClick: function() {
      this.dispatch($c.constants.$C_LOADING);
      return $c.ajaxPostJson("/ajax/logout", "", "application/json", $c.ajaxCallback.bind(this, "", $w.constants.$W_LOGOFF_SUCCESS));
    }
  };
  $w.PageStore = Fluxxor.createStore({
    initialize: function() {
      this.data = {};
    }
  });
  $w.flux = new Fluxxor.Flux();
  $w.pageStore = new $w.PageStore;
  $w.flux.addStore("PAGE", $w.pageStore);
  $w.flux.addActions($w.actions);
  $w.commonStore = new $c.CommonStore;
  $w.flux.addStore("COMMON", $w.commonStore);
  $w.flux.addActions($c.actions);
  $w.rcdStore = new $c.RcdStore;
  $w.flux.addStore("RCD", $w.rcdStore);
  $w.flux.addActions($c.rcdActions);
  rcdStore = $w.flux.store("RCD");
  rcdStore.addTable("systbl");
  $w.FluxMixin = Fluxxor.FluxMixin(React);
  $w.StoreWatchMixin = Fluxxor.StoreWatchMixin;
  $w.fix = function() {
    return $('#tablesystbl').tablefix({
      width: 900,
      height: 300,
      fixRows: 1
    });
  };
  $w.rcdStore.on("rcdComplete_systbl", function() {
    var rcdTemp, temp;
    rcdTemp = _.cloneDeep($w.app.state.rcd.systbl);
    temp = {
      systbl: $w.app.state.systbl
    };
    temp.systbl.rcds = rcdTemp.rcds;
    temp.form = rcdTemp.rcd;
    temp.systbl.selRow = rcdTemp.selRow;
    return $w.app.setState(temp);
  });
  $w.getDom = function(refname) {
    return $w.app.refs[refname].getDOMNode();
  };
  $w.scroll = function() {
    return $w.getDom("tableHead").scrollLeft = $w.getDom("tableBody").scrollLeft;
  };
  $w.onscroll = function() {
    return $w.getDom("tableBody").onscroll = $w.scroll;
  };
}).call(this);
