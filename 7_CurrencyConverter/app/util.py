from __future__ import annotations

import argparse


def ccy(value: str) -> str:
    v = value.strip().upper()
    if len(v) != 3 or not v.isalpha():
        raise argparse.ArgumentTypeError("currency must be a 3-letter code like EUR, USD, CHF")
    return v
