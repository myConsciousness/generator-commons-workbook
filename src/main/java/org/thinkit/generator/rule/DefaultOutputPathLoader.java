/**
 * Project Name : Generator<br>
 * File Name : DefaultOutputPathLoader.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/15<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.flogger.FluentLogger;

import org.thinkit.common.catalog.Platform;
import org.thinkit.common.rule.Attribute;
import org.thinkit.common.rule.Condition;
import org.thinkit.common.rule.Content;
import org.thinkit.common.rule.Rule;
import org.thinkit.generator.dto.DefaultOutputPath;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * 既定の出力先パスを管理するクラスです。
 * <p>
 * {@link #execute()} を実行することでコンテンツ「既定出力先情報」から既定の出力先を生成する際に必要な情報を取得します。
 * <p>
 * 実行の前提としてプログラム実行時のプラットフォームに対応した既定の出力先情報がコンテンツ「既定出力先情報」に定義されている必要があります。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class DefaultOutputPathLoader implements Rule<DefaultOutputPath> {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * プログラム実行時のプラットフォーム要素
     */
    @Getter(AccessLevel.PRIVATE)
    private Platform platform = null;

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private DefaultOutputPathLoader() {
    }

    /**
     * コンストラクタ
     *
     * @param platform プログラム実行時のプラットフォーム要素
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public DefaultOutputPathLoader(@NonNull Platform platform) {
        this.platform = platform;
    }

    /**
     * コンテンツ名定数
     */
    private enum ContentName implements Content {
        既定出力先情報;

        @Override
        public String getString() {
            return this.name();
        }
    }

    /**
     * コンテンツ要素定数
     */
    private enum ContentAttribute implements Attribute {
        環境変数名, 出力先ディレクトリ;

        @Override
        public String getString() {
            return this.name();
        }
    }

    /**
     * コンテンツ条件定数
     */
    private enum ContentConditions implements Condition {
        プラットフォームコード;

        @Override
        public String getString() {
            return this.name();
        }
    }

    @Override
    public DefaultOutputPath execute() {

        final Map<String, String> content = loadContent(ContentName.既定出力先情報).get(0);
        final DefaultOutputPath defaultOutputPath = DefaultOutputPath.of(
                content.get(ContentAttribute.環境変数名.getString()), content.get(ContentAttribute.出力先ディレクトリ.getString()));

        logger.atInfo().log("既定出力先 = (%s)", defaultOutputPath);
        return defaultOutputPath;
    }

    @Override
    public List<Attribute> getAttributes() {
        final List<Attribute> attributes = new ArrayList<>(2);
        attributes.add(ContentAttribute.環境変数名);
        attributes.add(ContentAttribute.出力先ディレクトリ);

        logger.atInfo().log("既定出力先パス情報のアトリビュート = (%s)", attributes);
        return attributes;
    }

    @Override
    public Map<Condition, String> getConditions() {
        final Map<Condition, String> conditions = new HashMap<>(1);
        conditions.put(ContentConditions.プラットフォームコード, String.valueOf(this.getPlatform().getCode()));

        logger.atInfo().log("既定出力先パス情報の条件 = (%s)", conditions);
        return conditions;
    }
}