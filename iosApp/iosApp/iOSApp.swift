// import SwiftUI
// import shared
//
//
// @main
// struct iOSApp: App {
// 	var body: some Scene {
// 		WindowGroup {
// 			ContentView()
// 			.onTapGesture {
//                                 // Hide keyboard on tap outside of TextField
//                                 UIApplication.shared.sendAction(#selector(UIResponder.resignFirstResponder), to: nil, from: nil, for: nil)
//                             }
// 		}
// 	}
// }


import Foundation
import SwiftUI
import shared

@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    var window: UIWindow?

    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        window = UIWindow(frame: UIScreen.main.bounds)
      let mainViewController = ApplicationKt.Main()
        window?.rootViewController = mainViewController
        window?.makeKeyAndVisible()
        return true
    }
}