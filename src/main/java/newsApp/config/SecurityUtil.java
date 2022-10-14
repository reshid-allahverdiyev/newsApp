package newsApp.config;

import java.util.List;

public class SecurityUtil {

    protected static final List<String> WHITE_LIST = List.of(
            "/ping",
            "/login",
            "/logout/**",
            "/news/allu"
    );

    public static boolean pathIsWhiteListed(String path){
        return WHITE_LIST.contains(path);
    }

}
