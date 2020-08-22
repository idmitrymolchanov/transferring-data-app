package psychotest.inner_datasource.actions.scripts;

import psychotest.inner_datasource.actions.init.InitScripts;
import psychotest.inner_datasource.actions.domain.DatasourceScriptsDomain;

public class DatasourceScripts implements Scripts {
    InitScripts initScripts;
    DatasourceScriptsDomain datasourceScript;

    public DatasourceScripts(InitScripts initScripts) {
        this.initScripts = initScripts;
    }

    @Override
    public void runScript() {
        initScripts.setConnection(datasourceScript.getDATASOURCE_CONNECTIONS_SCRIPT());
        initScripts.setConnection(datasourceScript.getDATASOURCE_SETTING_SCRIPT());
    }
}
