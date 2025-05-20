package org.etrange.sncfconnect.application.dtos

data class PageDto<T>(val page: Int, val pageSize: Int, val total: Long, val data: List<T>)
