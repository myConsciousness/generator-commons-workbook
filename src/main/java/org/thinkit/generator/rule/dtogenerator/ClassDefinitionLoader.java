/**
 * Project Name : Generator<br>
 * File Name : ClassDefinitionLoader.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/24<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.dtogenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.flogger.FluentLogger;

import org.thinkit.common.rule.Attribute;
import org.thinkit.common.rule.Condition;
import org.thinkit.common.rule.Content;
import org.thinkit.common.rule.Rule;

import lombok.EqualsAndHashCode;

import lombok.ToString;

/**
 * コンテンツ「クラス項目定義情報」の情報をロードするルールクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class ClassDefinitionLoader implements Rule<List<Map<String, String>>> {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * デフォルトコンストラクタ
     */
    public ClassDefinitionLoader() {
    }

    /**
     * コンテンツ名定数
     */
    private enum ContentName implements Content {
        クラス項目定義情報;

        @Override
        public String getString() {
            return this.name();
        }
    }

    /**
     * コンテンツ要素定数
     */
    private enum ContentAttribute implements Attribute {
        セル項目コード, セル項目名;

        @Override
        public String getString() {
            return this.name();
        }
    }

    @Override
    public List<Map<String, String>> execute() {
        return loadContent(ContentName.クラス項目定義情報);

    }

    @Override
    public List<Attribute> getAttributes() {
        final List<Attribute> attributes = new ArrayList<>(2);
        attributes.add(ContentAttribute.セル項目コード);
        attributes.add(ContentAttribute.セル項目名);

        logger.atInfo().log("クラス項目定義情報のアトリビュート = (%s)", attributes);
        return attributes;
    }

    @Override
    public Map<Condition, String> getConditions() {
        return Map.of();
    }
}