package br.com.rabbithole.core.annotations;

import java.lang.annotation.*;

/**
 * @author Felipe Ros
 * @Usage Indicar que o recurso utilizado está em Beta.
 * @since 1.2
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Beta {
}
