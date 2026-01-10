from __future__ import annotations

import argparse
import sys

from app.api import get_latest_rate
from app.format import print_rate_table, print_convert_table
from app.util import ccy


def build_parser() -> argparse.ArgumentParser:
    p = argparse.ArgumentParser(
        description="Small FX CLI (rate + convert) using Frankfurter API (no key)."
    )
    p.add_argument("--no-cache", action="store_true", help="Disable local caching")
    sub = p.add_subparsers(dest="cmd", required=True)

    p_rate = sub.add_parser("rate", help="Get latest exchange rate BASE -> QUOTE")
    p_rate.add_argument("--base", type=ccy, required=True)
    p_rate.add_argument("--quote", type=ccy, required=True)

    p_conv = sub.add_parser("convert", help="Convert AMOUNT in BASE to QUOTE using latest rate")
    p_conv.add_argument("--base", type=ccy, required=True)
    p_conv.add_argument("--quote", type=ccy, required=True)
    p_conv.add_argument("--amount", type=float, required=True)

    return p


def main() -> int:
    args = build_parser().parse_args()
    no_cache: bool = args.no_cache

    if args.cmd == "rate":
        date_iso, rate = get_latest_rate(args.base, args.quote, no_cache=no_cache)
        print_rate_table(date_iso, args.base, args.quote, rate)
        return 0

    if args.cmd == "convert":
        date_iso, rate = get_latest_rate(args.base, args.quote, no_cache=no_cache)
        converted = args.amount * rate
        print_convert_table(date_iso, args.amount, args.base, args.quote, rate, converted)
        return 0

    return 1


if __name__ == "__main__":
    try:
        raise SystemExit(main())
    except KeyboardInterrupt:
        print("\nCancelled.", file=sys.stderr)
        raise SystemExit(130)
