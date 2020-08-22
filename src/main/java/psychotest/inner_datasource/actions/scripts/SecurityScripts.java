package psychotest.inner_datasource.actions.scripts;

import psychotest.inner_datasource.actions.init.InitScripts;
import psychotest.inner_datasource.actions.domain.SecurityScriptsDomain;

public class SecurityScripts implements Scripts {
    InitScripts initScripts;
    SecurityScriptsDomain securityScript;

    public SecurityScripts(InitScripts initScripts) {
        this.initScripts = initScripts;
    }

    @Override
    public void runScript() {
        initScripts.setConnection(securityScript.getAPP_ROLE_SCRIPT());
        initScripts.setConnection(securityScript.getAPP_USER_SCRIPT());
        initScripts.setConnection(securityScript.getPERSISTENT_LOGINS_SCRIPT());
        initScripts.setConnection(securityScript.getUSER_ROLE_SCRIPT());
    }
}
