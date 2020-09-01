package com.freddy.kulachat.ims;

import com.freddy.kulachat.ims.config.IMSOptions;

/**
 * @author FreddyChen
 * @name
 * @date 2020/08/31 19:19
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 */
public class IMSKit {

    private IMSKit() {
    }

    public static IMSKit getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        private static final IMSKit INSTANCE = new IMSKit();
    }

    public void init(IMSOptions options) {

    }
}
