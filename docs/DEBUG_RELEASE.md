# Debug 測試版本說明

## 版本資訊

- 版本：`1.0.1-debug`
- Git 標籤：`v1.0.1-debug`
- 發布狀態：GitHub Pre-release
- APK：`dropdown-status-bar-v1.0.1-debug.apk`
- 簽章：Android Debug Key
- SHA-256：`e3b7a0621510a6de614a06000a8ff4d1c7c29daf09c1f518ad9a9b3751f174ef`

## 重要警告

此 APK 是開發階段的 Debug 測試版本，不是正式版本。

- 僅供功能驗證及問題回報。
- Debug 簽章不適合正式發布。
- 未來版本可能無法直接覆蓋安裝，屆時需要先移除舊版。
- 不應將此 APK 提交至 Google Play 或其他正式商店。
- 不承諾資料、設定或升級相容性。

## 本次更新

- App 名稱統一為「下拉通知欄」。
- 更新為活潑、友善、年輕化的粗線條卡通圖示。
- 使用符合安全區規格的前景、背景及單色 Adaptive Icon 資源。

## 已驗證項目

- Debug APK 建置成功。
- Release 程式碼建置成功，但未配置正式簽章。
- Android Lint 完成且沒有錯誤。
- APK 最低支援 Android 8.0。
- APK 目標版本為 Android API 36。
- APK 不包含網路權限。

## 尚待實機驗證

目前仍應在不同品牌裝置驗證：

- App 圖示一鍵展開通知欄。
- 一鍵展開快速設定。
- 兩種 `1×1` 桌面小工具。
- 長按 App 圖示進入設定後，下一次點擊仍執行正常功能。
- 重開機及省電模式下的無障礙服務狀態。
- 鎖定畫面、橫向畫面及工作設定檔行為。

建議至少測試 Pixel、Samsung、Xiaomi 及 OPPO／realme 裝置。
