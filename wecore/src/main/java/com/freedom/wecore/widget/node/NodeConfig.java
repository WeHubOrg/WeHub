package com.freedom.wecore.widget.node;


/**
 * @author vurtne on 3-May-18.
 */
public class NodeConfig {

    public static final int METAMORPHOSIS = 3;
    public static final int DILIGENT  = 2;
    public static final int NEGATIVE = 1;
    public static final int LAZY = 0;

    private int sCurState = 3;

    private static NodeConfig sConfig;

    private final static Object LOCK = new Object();

    private NodeConfig(){

    }

    public static NodeConfig instance(){
        if (sConfig == null){
            synchronized (LOCK){
                if (sConfig == null){
                    sConfig = new NodeConfig();
                }
            }
        }
        return sConfig;
    }

    public int getCurState() {
        return sCurState;
    }

    public void setCurState(int state) {
        this.sCurState = state;
    }
}
