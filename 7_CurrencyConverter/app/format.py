from __future__ import annotations

from typing import List, Tuple


def _format_table(headers: List[str], rows: List[List[str]]) -> str:
    cols = len(headers)
    widths = [len(h) for h in headers]
    for row in rows:
        for i in range(cols):
            widths[i] = max(widths[i], len(row[i]))

    def sep(char: str = "-") -> str:
        parts = [char * (w + 2) for w in widths]
        return "+" + "+".join(parts) + "+"

    def line(items: List[str]) -> str:
        cells = [f" {items[i].ljust(widths[i])} " for i in range(cols)]
        return "|" + "|".join(cells) + "|"

    out = []
    out.append(sep("-"))
    out.append(line(headers))
    out.append(sep("="))
    for r in rows:
        out.append(line(r))
        out.append(sep("-"))
    return "\n".join(out)


def print_rate_table(date_iso: str, base: str, quote: str, rate: float) -> None:
    headers = ["date", "pair", "rate"]
    rows = [[date_iso, f"{base}->{quote}", f"{rate:.6f}"]]
    print(_format_table(headers, rows))


def print_convert_table(date_iso: str, amount: float, base: str, quote: str, rate: float, converted: float) -> None:
    headers = ["date", "amount", "base", "quote", "rate", "converted"]
    rows = [[
        date_iso,
        f"{amount:.2f}",
        base,
        quote,
        f"{rate:.6f}",
        f"{converted:.2f}",
    ]]
    print(_format_table(headers, rows))
