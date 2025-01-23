package sd.gov.moe.lp.data.access.utils

interface ModelWithId<Key : Comparable<Key>> {
    val id: Key?
}

val <Key : Comparable<Key>> ModelWithId<Key>.validId: Key
    get() {
        return this.id ?: throw IllegalArgumentException("${this::class.simpleName} id cannot be null")
    }