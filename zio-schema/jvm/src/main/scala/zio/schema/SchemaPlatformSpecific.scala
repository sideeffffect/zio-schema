package zio.schema

import java.net.URI

trait SchemaPlatformSpecific {

  implicit val url: Schema[java.net.URL] =
    Schema[String].transformOrFail(
      string =>
        try {
          Right(new URI(string).toURL)
        } catch {
          case _: Exception => Left(s"Invalid URL: $string")
        },
      url => Right(url.toString)
    )
}
