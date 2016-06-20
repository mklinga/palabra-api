import javax.inject._
import play.api._
import play.api.http.HttpFilters
import play.api.mvc._

import filters.CorsFilter

@Singleton
class Filters @Inject() (
  env: Environment,
  corsFilter: CorsFilter) extends HttpFilters {

  override val filters = {
    if (env.mode == Mode.Dev) Seq(corsFilter) else Seq.empty
  }

}
