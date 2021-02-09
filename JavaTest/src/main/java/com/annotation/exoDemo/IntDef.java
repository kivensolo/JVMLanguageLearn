package com.annotation.exoDemo;/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Copy from android.support.annotation;
 * 表示带注释的元素是整数类型的，
 * 表示逻辑类型，其值应该是显式命名的常量之一。
 * 如果IntDef#flag()属性设置为true，则可以组合多个常量。
 *
 * <p>
 * Example:
 * <pre><code>
 *  @Retention(SOURCE)
 *  @IntDef({NAVIGATION_MODE_STANDARD, NAVIGATION_MODE_LIST, NAVIGATION_MODE_TABS})
 *  public @interface NavigationMode {}
 *
 *  public static final int NAVIGATION_MODE_STANDARD = 0;
 *  public static final int NAVIGATION_MODE_LIST = 1;
 *  public static final int NAVIGATION_MODE_TABS = 2;
 *  ...
 *  public abstract void setNavigationMode(@NavigationMode int mode);
 *
 *  @NavigationMode
 *  public abstract int getNavigationMode();
 *
 * </code></pre>
 * For a flag, set the flag attribute:
 * <pre><code>
 *  @IntDef(
 *      flag = true,
 *      value = {NAVIGATION_MODE_STANDARD, NAVIGATION_MODE_LIST, NAVIGATION_MODE_TABS})
 * </code></pre>
 *
 * @see LongDef
 */
@Retention(SOURCE)
@Target({ANNOTATION_TYPE})
public @interface IntDef {
    /** Defines the allowed constants for this element */
    int[] value() default {};

    /** Defines whether the constants can be used as a flag, or just as an enum (the default) */
    boolean flag() default false;
}
