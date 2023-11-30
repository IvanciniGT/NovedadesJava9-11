import com.dni.DNIUtils;
import com.impl.DNIUtilsImpl;

module dni.util.impl {

    requires dni.utils.api;
    requires lombok;
    provides DNIUtils with DNIUtilsImpl;

}