package geodatahubback.config.concurrent;

import java.lang.annotation.*;

/**
 * @Description
 * @Auther wyjq
 * @Date 2023/5/9
 **/

@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface RateLimit {

}
