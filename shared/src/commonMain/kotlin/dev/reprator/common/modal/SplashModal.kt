package dev.reprator.common.modal

data class SplashModal(val imageList:List<String>, val languageList: List<LanguageModal>)
data class LanguageModal(val id: Int, val name: String)