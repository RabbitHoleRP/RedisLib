package br.com.rabbithole.core.annotations;

import java.lang.annotation.*;

/**
 * @author Felipe Ros
 * @Usage Indicar a versão que o recurso foi adicionado.
 * @since 1.2
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Since {
    String version();
}
