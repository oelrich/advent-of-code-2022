(def current-time
  (.format
   (.withZoneSameInstant
    (java.time.ZonedDateTime/now)
    (java.time.ZoneId/of "Europe/Stockholm"))
   (java.time.format.DateTimeFormatter/ofPattern "HH:mm")))
(println current-time)