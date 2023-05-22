//
//  NetworkDetectorImpl.swift
//  iosApp
//
//  Created by Vikram Singh on 22/05/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import Network
import shared

final class NetworkDetectorImpl: ObservableObject, NetworkDetector {
    
    var isConnected: Bool = false
    
    private let nwMonitor = NWPathMonitor()
    private let workerQueue = DispatchQueue.global()
    
    func startMonitor() {
        nwMonitor.start(queue: workerQueue)
        nwMonitor.pathUpdateHandler = { [weak self] path in
            DispatchQueue.main.async {
                self?.isConnected = path.status == .satisfied
            }
        }
    }
    
    func stopMonitor() {
        nwMonitor.cancel()
    }
}
