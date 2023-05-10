import UIKit
import Foundation
import SwiftUI
import shared


@main
struct iosApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

struct ContentView: View {
    var body: some View {
        ComposeView().ignoresSafeArea(.keyboard)
    }
}

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        ApplicationKt.Main()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}
