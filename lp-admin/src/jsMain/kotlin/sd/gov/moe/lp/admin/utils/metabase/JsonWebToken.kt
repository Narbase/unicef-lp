package sd.gov.moe.lp.admin.utils.metabase

@JsModule("jsonwebtoken/sign")
@JsName("sign")
@JsNonModule
external fun jwsSign(payload: dynamic, secretOrPrivateKey: dynamic, options: dynamic): String
