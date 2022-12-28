package com.example.temp.todo.models.passport.response

data class Data(
    val country_eng_nm: String,
    val country_iso_alp2: String,
    val country_nm: String,
    val dplmt_pspt_visa_cn: String,
    val dplmt_pspt_visa_yn: String,
    val gnrl_pspt_visa_cn: String,
    val gnrl_pspt_visa_yn: String,
    val have_yn: String,
    val id: Int,
    val nvisa_entry_evdc_cn: String,
    val ofclpspt_visa_cn: String,
    val ofclpspt_visa_yn: String,
    val remark: String
)