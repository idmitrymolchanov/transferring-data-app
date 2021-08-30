package psychotest.inner_datasource.actions.scripts;

import psychotest.inner_datasource.actions.domain.ReportScriptDomain;
import psychotest.inner_datasource.actions.init.InitScripts;

public class ReportScript implements Scripts{
    InitScripts initScripts;
    ReportScriptDomain reportScript;

    public ReportScript(InitScripts initScripts) {
        this.initScripts = initScripts;
    }

    @Override
    public void runScript() {
        initScripts.setConnection(reportScript.getREPORT_SCRIPT());
    }
}
