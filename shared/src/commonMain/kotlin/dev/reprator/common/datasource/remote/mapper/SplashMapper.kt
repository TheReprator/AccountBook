package dev.reprator.common.datasource.remote.mapper

import dev.reprator.common.datasource.remote.modal.SplashEntity
import dev.reprator.common.modal.LanguageModal
import dev.reprator.common.modal.SplashModal
import dev.reprator.common.util.Mapper

class SplashMapper: Mapper<SplashEntity, SplashModal> {

    override suspend fun map(from: SplashEntity): SplashModal {

        val languageList = from.languageList?.map {
            LanguageModal(it?.id ?: 0, it?.name.orEmpty())
        } ?: emptyList()

        val imageList: List<String> = from.imageList?.filterNotNull() ?: emptyList()

        return SplashModal(imageList, languageList)
    }
}