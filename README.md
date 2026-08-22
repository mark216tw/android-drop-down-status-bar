# 下拉通知欄

[![授權條款](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Android](https://img.shields.io/badge/Android-8.0%2B-3DDC84.svg)](docs/DEVELOPMENT.md)
[![版本](https://img.shields.io/badge/版本-1.0.0--debug-orange.svg)](https://github.com/mark216tw/android-drop-down-status-bar/releases)

「下拉通知欄」是一個輕量 Android 工具，讓使用者透過 App 圖示或桌面小工具，一鍵展開通知欄或快速設定。主要用途是改善大螢幕及單手操作時，不容易觸碰螢幕頂端的問題。

> [!WARNING]
> 目前 GitHub Release 提供的是 **Debug 測試版本**，使用 Android Debug Key 簽署，僅供功能測試，不是正式發行版本，請勿用於正式部署。

## 功能

- 點擊 App 圖示執行預設動作
- 一鍵展開通知欄
- 一鍵展開快速設定
- 通知欄與快速設定 `1×1` 桌面小工具
- 無障礙服務啟用、狀態檢查及醒目權限說明
- 成功執行後的選用震動回饋
- 長按 App 圖示可開啟設定
- 繁體中文及英文介面
- 跟隨系統深色模式
- App 內建隱私權政策

## 系統需求

- Android 8.0（API 26）以上
- 必須由使用者手動啟用「下拉通知欄」無障礙服務
- 部分品牌裝置可能受到省電策略、鎖定畫面或企業管理政策限制

## 安裝 Debug 版本

1. 前往 [GitHub Releases](https://github.com/mark216tw/android-drop-down-status-bar/releases)。
2. 找到標示為 **Pre-release** 的 `v1.0.0-debug`。
3. 下載 `dropdown-status-bar-v1.0.0-debug.apk`。
4. 依 Android 提示允許該來源安裝未知應用程式。
5. 安裝後確認 App 名稱包含「Debug」，以免與未來正式版混淆。

完整操作方式請參閱[使用指南](docs/USER_GUIDE.md)。

## 隱私與權限

本 App：

- 不包含網路權限
- 不要求通知存取權
- 不讀取通知內容
- 不擷取畫面內容
- 不收集輸入文字或其他個人資料
- 只在本機儲存預設動作及震動偏好

無障礙服務的 `canRetrieveWindowContent` 設為 `false`，只執行使用者主動要求的 Android 全域動作。詳細內容請參閱[隱私權政策](docs/PRIVACY_POLICY.md)。

## 自行建置

需要 Java 17、Android SDK 36 及可連線下載 Gradle 相依套件的環境。

Windows：

```powershell
.\gradlew.bat assembleDebug
```

macOS 或 Linux：

```bash
./gradlew assembleDebug
```

產出的 APK 位於：

```text
app/build/outputs/apk/debug/dropdown-status-bar-v1.0.0-debug.apk
```

開發環境與驗證指令請參閱[開發文件](docs/DEVELOPMENT.md)。

## 文件

- [使用指南](docs/USER_GUIDE.md)
- [開發與建置](docs/DEVELOPMENT.md)
- [Debug 測試版本說明](docs/DEBUG_RELEASE.md)
- [隱私權政策](docs/PRIVACY_POLICY.md)
- [版本紀錄](CHANGELOG.md)

## 授權

本專案採用 [MIT License](LICENSE) 授權。
