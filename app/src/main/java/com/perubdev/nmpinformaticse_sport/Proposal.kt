package com.perubdev.nmpinformaticse_sport

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Proposal(var idjoin_proposal: Int,
                    var idmember: Int,
                    var game: String,
                    var idteam: Int,
                    var description:String,
                    var status: String): Parcelable
