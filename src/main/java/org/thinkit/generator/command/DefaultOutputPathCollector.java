/**
 * Project Name : Generator<br>
 * File Name : DefaultOutputPathCollector.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/31<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.command;

import org.thinkit.common.catalog.Platform;
import org.thinkit.common.command.Command;
import org.thinkit.common.rule.RuleInvoker;
import org.thinkit.generator.rule.DefaultOutputPathLoader;
import org.thinkit.generator.vo.DefaultOutputPath;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * プログラム実行時のプラットフォームに応じた既定出力先を取得する処理を定義したコマンドクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class DefaultOutputPathCollector implements Command<DefaultOutputPath> {

    /**
     * プログラム実行時のプラットフォーム要素
     */
    private Platform platform;

    /**
     * デフォルトコンストラクタ
     */
    private DefaultOutputPathCollector() {
    }

    /**
     * コンストラクタ
     *
     * @param platform プログラム実行時のプラットフォーム要素
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DefaultOutputPathCollector(@NonNull Platform platform) {
        this.platform = platform;
    }

    /**
     * 引数として与えられた {@code platform} をもとに {@link DefaultOutputPathCollector}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param platform プログラム実行時のプラットフォーム
     * @return {@link DefaultOutputPathCollector} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     * @see Platform
     */
    public static DefaultOutputPathCollector of(@NonNull Platform platform) {
        return new DefaultOutputPathCollector(platform);
    }

    @Override
    public DefaultOutputPath run() {
        return RuleInvoker.of(DefaultOutputPathLoader.of(platform)).invoke();
    }
}