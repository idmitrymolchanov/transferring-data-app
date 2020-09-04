package psychotest.inner_datasource.actions.scripts;

import psychotest.inner_datasource.actions.init.InitScripts;
import psychotest.inner_datasource.actions.domain.TablesScriptDomain;

public class TablesScripts implements Scripts {
    InitScripts initScripts;
    TablesScriptDomain sendTableScript;

    public TablesScripts(InitScripts initScripts) {
        this.initScripts = initScripts;
    }

    @Override
    public void runScript() {
        initScripts.setConnection(sendTableScript.getTYPE_AND_VALUE_SCRIPT());
        initScripts.setConnection(sendTableScript.getSCHEDULER_SCRIPT());
        initScripts.setConnection(sendTableScript.getTABLE_NAME_SCRIPT());
        initScripts.setConnection(sendTableScript.getUNIQUE_VALUES_SCRIPT());
    }
}
