# Prerelease 測試版本說明

## 版本資訊

- 版本：`1.0.2-prerelease`
- Git 標籤：`v1.0.2-debug`
- 發布狀態：GitHub Pre-release
- Build Type：`prerelease`
- APK：`dropdown-status-bar-v1.0.2-prerelease.apk`
- 壓縮：R8 已啟用
- 簽章：Android Debug Key
- SHA-256：`c347aeac4de2f5d4408ac99d67f635f7d5b27153489cc907beddfb906289bc2a`

## 重要警告

此 APK 是啟用 R8 壓縮並使用 Debug 金鑰簽署的 Prerelease 測試版本，不是正式版本。

- 僅供功能驗證及問題回報。
- Debug 簽章不適合正式發布。
- 未來版本可能無法直接覆蓋安裝，屆時需要先移除舊版。
- 不應將此 APK 提交至 Google Play 或其他正式商店。
- 不承諾資料、設定或升級相容性。

## 本次更新

- 新增系統、淺色及深色顯示模式。
- 新增六種可即時切換的主題色彩。
- 狀態列及導覽列會跟隨介面明暗模式。
- App 圖示改為單一淡紫色背景。
- 新增 `prerelease` Build Type 並啟用 R8 壓縮。

## 已驗證項目

- Prerelease APK 建置成功。
- R8 mapping 檔案已產生。
- APK 已使用 Android Debug Key 簽署。
- Release 程式碼建置成功，但未配置正式簽章。
- Android Lint 完成且沒有錯誤。
- APK 最低支援 Android 8.0。
- APK 目標版本為 Android API 36。
- APK 不包含網路權限。

## 尚待實機驗證

目前仍應在不同品牌裝置驗證：

- App 圖示一鍵展開通知欄。
- 一鍵展開快速設定。
- 三種 `1×1` 桌面小工具。
- 長按 App 圖示進入設定後，下一次點擊仍執行正常功能。
- 重開機及省電模式下的無障礙服務狀態。
- 鎖定畫面、橫向畫面及工作設定檔行為。

建議至少測試 Pixel、Samsung、Xiaomi 及 OPPO／realme 裝置。
