from __future__ import annotations

import json
from pathlib import Path
from typing import Any, Dict, Optional

import requests

CACHE_DIR = Path(".app_cache")
CACHE_DIR.mkdir(exist_ok=True)


def _cache_path(key: str) -> Path:
    safe = (
        key.replace("/", "_")
        .replace("?", "_")
        .replace("&", "_")
        .replace("=", "_")
        .replace(":", "_")
        .replace("..", "_")
        .strip()
    )
    return CACHE_DIR / f"{safe}.json"


def _http_get_json(url: str, params: Optional[dict] = None, timeout_s: int = 8) -> Dict[str, Any]:
    try:
        r = requests.get(url, params=params, timeout=timeout_s)
        r.raise_for_status()
        return r.json()
    except requests.RequestException as e:
        raise RuntimeError(f"HTTP request failed: {e}") from e


def cached_get_json(
    key: str,
    url: str,
    params: Optional[dict] = None,
    no_cache: bool = False,
) -> Dict[str, Any]:
    p = _cache_path(key)

    if (not no_cache) and p.exists():
        return json.loads(p.read_text(encoding="utf-8"))

    data = _http_get_json(url, params=params)

    if not no_cache:
        p.write_text(json.dumps(data, ensure_ascii=False, indent=2), encoding="utf-8")

    return data
