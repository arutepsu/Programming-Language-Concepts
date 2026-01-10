from __future__ import annotations

from typing import Tuple

from .cache import cached_get_json

API_BASE = "https://api.frankfurter.dev/v1"


def get_latest_rate(base: str, quote: str, no_cache: bool = False) -> Tuple[str, float]:
    """
    Calls: /v1/latest?base=BASE&symbols=QUOTE
    Returns: (date_iso, rate)
    """
    url = f"{API_BASE}/latest"
    params = {"base": base, "symbols": quote}
    key = f"latest_base={base}_quote={quote}"

    data = cached_get_json(key, url, params=params, no_cache=no_cache)

    date_iso = data.get("date")
    rates = data.get("rates", {})
    rate = rates.get(quote)

    if not isinstance(date_iso, str):
        raise RuntimeError("API response missing 'date'")
    if not isinstance(rate, (int, float)):
        raise RuntimeError(f"API response missing rate for {base}->{quote}")

    return date_iso, float(rate)
