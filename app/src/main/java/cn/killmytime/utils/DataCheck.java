package cn.killmytime.utils;

/**
 * Created by leiwe on 2018/5/17.
 * Thank you for reading, everything gonna to be better.
 */
public class DataCheck {
    //Todo to be applied
    public static boolean isExistUsername(String username){
        return false;
    }

    public static boolean isStrongPassword(String password){
        String regex="^$";
        if (password.length()<8)
            return false;
        else {
            //ToDo need to be checked
        return password.matches("^.*[a-zA-Z]+.*$") && password.matches("^.*[0-9]+.*$")
                && password.matches("^.*[/^/$/.//,;:'!@#%&/*/|/?/+/(/)/[/]/{/}]+.*$");
    }
    }
}
