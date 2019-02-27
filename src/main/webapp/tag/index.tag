<content>
    <div style={
        "width:900px;height:600px;background-color:#F0F0F0;border:1px;border-style:solid;color:black;font-size:14px;"}>

        <div class="row" style={
            "height:40px;color:#fff;background-color:#015666;font-size:20px;padding-top:5px;margin-right:0px;"}>
            <div class="col-xs-6">
                <div style={ "margin-left:20px;text-align:center;width:700px;"}>
                    {"Hello World"}</div>


            </div>

            <div class="col-xs-4">

            </div>

        </div>
          <div class="row" style={"margin-top:20px;"}>
            <div class="col-xs-4">
                <button type="button" class="btn btn-primary" onclick={$w.app.testClick} style={ "margin-left:60px;"
                    }>TEST</button>
                <input type="text" style={ "width:100px;margin-left:20px;" } name="test" value={$ws.test}
                    onchange={$c.onChange}>
            </div>
            <div class="col-xs-4">
                <input type="checkbox" style={ "width:20px;margin-left:20px;" } name="setting#test"
                    checked={$ws.setting.test} onchange={$c.onChecked}
                <span style="margin-left:0px;">チェックボックス</span>

            </div>
            </div>
             <div class="row" style={"margin-top:20px;"}>
            <div class="col-xs-4">
                <input class="form-check-input" type="checkbox" name="sel#1" checked={$ws.sel=="1" }
                    onchange={$c.onRadioChecked} style="width:18px;height:18px;margin-top:1px;margin-left:120px;" />
                <span style="padding-top:10px;vertical-align:top">SEL1 </span>
                <input class="form-check-input" type="checkbox" name="sel#2" checked={$ws.sel=="2" }
                    onchange={$c.onRadioChecked} style="width:18px;height:18px;margin-top:1px;margin-left:30px;" />
                <span style="padding-top:10px;vertical-align:top">SEL2</span>
                 </div>
                <div class="col-xs-4">
                <select value={$ws.testsel} name={"testsel"} onChange={$c.onChange}
                    style={"margin-left:0px;margin-top:0px;"}>
                    <option each={c,index in $ws.testgroup} value={c.value} selected={c.value==$ws.testsel}>
                    {c.label}</option>
                </select>
            </div>

                </div>
   
            <table class="table table-bordered table-condensed wscrolltable" id="stable"
             style="width:300px;margin-left:120px;" if={$ws.setting.test}>
            
                  <tr>
                    <th style={"width:150px;"}>VALUE</th>
                    <th style={"width:150px;"}>LABEL</th>
                  </tr>
                </thead>
                <tbody >
                  <tr each={c,index in  $ws.testgroup} >
                    <td>{c.value}</td>
                    <td>{c.label}</td>

                  </tr>
                </tbody>
              </table>










        </div>
        </div>
    </div>

    <script>
        $c.checkAndCreate("$w");
        

        $w.app = this
        $wa = $w.app
        
        $w.app.state = { 
            setting:{},
            testsel:"",
            testgroup:[{label:"opt1",value:1},{label:"opt2",value:2},{label:"opt3",value:3}],
            swal: {
                show: false,
                title: "",
                type: "",
                func: ""
            },
        
        };
        $ws = $w.app.state
        loginCallBack(){
            
        }
        testCallBack(res,status){
            var r=JSON.parse(res)
            var x=status
               if ($c.ErrorCheck(r.response)==false){
                return
                } 
                $wa.update()
        }
        unload() {
			//$c.ajaxPostJsonSwal("ajax/quit", {}, $w.app.loginCallBack)
		}
        testClick(){
              $c.ajaxPostJsonSwal("ajax/test", {in:$ws.test}, $w.app.testCallBack)
        }
        this.on('mount', function () {
        })
        this.on('update', function () {
            if ($w.app.state.swal.show) {
                $c.swalshow()
            }

        })
    </script>

    <loginModal>
    
</content>

