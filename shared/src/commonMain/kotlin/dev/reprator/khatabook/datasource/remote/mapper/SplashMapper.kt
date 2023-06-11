package dev.reprator.khatabook.datasource.remote.mapper

import dev.reprator.khatabook.datasource.remote.modal.SplashEntity
import dev.reprator.khatabook.modal.LanguageModal
import dev.reprator.khatabook.modal.SplashModal
import dev.reprator.khatabook.util.Mapper

class SplashMapper: Mapper<SplashEntity, SplashModal> {

    override suspend fun map(from: SplashEntity): SplashModal {

        val languageList = from.languageList?.map {
            LanguageModal(it?.id ?: 0, it?.name.orEmpty())
        } ?: emptyList()

        val imageList: List<String> = from.imageList?.filterNotNull() ?: emptyList()

        return SplashModal(imageList, languageList)
    }
}