# 開發與建置

## 技術規格

- 語言：Kotlin
- 最低版本：Android 8.0，API 26
- 編譯版本：Android API 36
- 目標版本：Android API 36
- Java：17
- Gradle Wrapper：8.11.1
- Android Gradle Plugin：8.10.1
- Kotlin Gradle Plugin：2.1.21

專案使用 Android Framework UI，不依賴第三方 UI 套件。

## 核心元件

| 元件 | 用途 |
|---|---|
| `ActionActivity` | 透明的一鍵操作入口 |
| `MainActivity` | 權限引導及偏好設定 |
| `PanelAccessibilityService` | 執行 Android 全域面板動作 |
| `PanelWidgetProvider` | 更新兩種桌面小工具 |
| `AppPreferences` | 儲存預設動作及震動設定 |

## 無障礙服務設計

服務只呼叫以下 Android API：

- `GLOBAL_ACTION_NOTIFICATIONS`
- `GLOBAL_ACTION_QUICK_SETTINGS`

服務設定明確停用視窗內容擷取：

```xml
android:canRetrieveWindowContent="false"
```

專案不宣告網路權限或通知存取權。

## Prerelease 建置

Windows：

```powershell
.\gradlew.bat assemblePrerelease
```

macOS 或 Linux：

```bash
./gradlew assemblePrerelease
```

Prerelease 版本具有以下識別：

- 版本名稱：`1.0.2-prerelease`
- Build Type：`prerelease`
- App 名稱：`下拉通知欄`
- APK：`dropdown-status-bar-v1.0.2-prerelease.apk`
- 啟用 R8 程式碼壓縮與最佳化
- 使用 Android Debug Key 簽署

## 程式品質驗證

```powershell
.\gradlew.bat lintPrerelease
```

也可以一次執行建置及 Lint：

```powershell
.\gradlew.bat assemblePrerelease lintPrerelease
```

## Release 建置

目前 repository 未包含正式簽章金鑰或正式簽章設定。`assembleRelease` 產生的 APK 為未簽署檔案，不應直接對外發布。

正式發布前應完成：

- 建立並安全保存正式簽章金鑰
- 透過環境變數或本機未提交檔案提供簽章資訊
- 完成多品牌 Android 實機測試
- 更新版本編號及版本紀錄
- 重新確認 Google Play Accessibility API 政策

任何簽章金鑰、密碼或 `local.properties` 都不得提交到 Git。
