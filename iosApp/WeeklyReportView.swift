import SwiftUI

struct WeeklyReportView: View {
    @State private var blockers: String = "None this week."
    @State private var personalNotes: String = "Learned KMP StateFlow bridging to SwiftUI on iOS 18."
    @State private var copied: Bool = false

    var body: some View {
        NavigationStack {
            List {
                Section {
                    VStack(alignment: .leading, spacing: 6) {
                        Text("Weekly Report: Dev Jane")
                            .font(.headline)
                            .bold()
                        Text("2026-10-14 - 2026-10-20")
                            .font(.subheadline)
                            .foregroundColor(.secondary)
                    }
                    .padding(.vertical, 4)
                }

                Section("Major Highlights (1)") {
                    ActivityRow(
                        repo: "sample-org/mobile-client",
                        title: "feat: implement offline report draft caching"
                    )
                }

                Section("Minor & Chores (2)") {
                    ActivityRow(
                        repo: "sample-org/mobile-client",
                        title: "fix: resolve keychain entitlement warning in debug builds"
                    )
                    ActivityRow(
                        repo: "sample-org/tooling-core",
                        title: "chore(deps): bump kotlin to 2.1.10 and coroutines to 1.10.1"
                    )
                }

                Section("Reviews & Support (1)") {
                    ActivityRow(
                        repo: "sample-org/mobile-client",
                        title: "CR: refactor navigation flow for tablet split view"
                    )
                }

                Section("Blockers") {
                    TextField("Any blockers this week?", text: $blockers, axis: .vertical)
                        .lineLimit(2...4)
                }

                Section("Personal Notes & Learnings") {
                    TextField("What did you explore?", text: $personalNotes, axis: .vertical)
                        .lineLimit(2...4)
                }
            }
            .navigationTitle("Spaces")
            .safeAreaInset(edge: .bottom) {
                Button(action: copyToClipboard) {
                    HStack {
                        Image(systemName: copied ? "checkmark" : "doc.on.doc")
                        Text(copied ? "Copied!" : "Copy as Markdown")
                            .bold()
                    }
                    .frame(maxWidth: .infinity)
                    .padding()
                    .background(Color.accentColor)
                    .foregroundColor(.white)
                    .cornerRadius(12)
                    .padding(.horizontal)
                    .padding(.bottom, 8)
                }
            }
        }
    }

    private func copyToClipboard() {
        let markdown = """
        ## Weekly Progress Report: Dev Jane
        **Period:** 2026-10-14 - 2026-10-20

        ### Major Work
        - **[sample-org/mobile-client]** feat: implement offline report draft caching

        ### Minor & Maintenance
        - **[sample-org/mobile-client]** fix: resolve keychain entitlement warning in debug builds
        - **[sample-org/tooling-core]** chore(deps): bump kotlin to 2.1.10 and coroutines to 1.10.1

        ### Code Reviews & Support
        - Reviewed: CR: refactor navigation flow for tablet split view (sample-org/mobile-client)

        ### Blockers
        \(blockers)

        ### Notes & Learning
        \(personalNotes)
        """
        UIPasteboard.general.string = markdown
        withAnimation {
            copied = true
        }
        DispatchQueue.main.asyncAfter(deadline: .now() + 2) {
            copied = false
        }
    }
}

struct ActivityRow: View {
    let repo: String
    let title: String

    var body: some View {
        VStack(alignment: .leading, spacing: 4) {
            Text(repo)
                .font(.caption)
                .foregroundColor(.secondary)
            Text(title)
                .font(.body)
        }
        .padding(.vertical, 2)
    }
}

#Preview {
    WeeklyReportView()
}
