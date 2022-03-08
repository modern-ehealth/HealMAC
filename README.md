# HealMAC

Human-friendly Message Authentication Codes for Healthcare Services!

## Description

Sometimes it's necessary to validate the authenticity of messages as part of a process that involves human interaction. In these cases it's error-prone and cumbersome to use long Message Authentication Codes generatedy by the more intense cryptographic hashes (512 bits?!).

That's why Modern eHealth's open source group has created an alternative that is human-friendly enough for people to read the authentication codes over the telephone if needed.

The HealMAC library can be used to both generate and validate codes, which generated using the modern [Murmur3 hash function](https://en.wikipedia.org/wiki/MurmurHash). For maximum readability it is recommended to encode the MAC using something like [Base36](https://en.wikipedia.org/wiki/Base36) so that only familiar English alphabet letters and digits are presented to the user.

### Changelog

#### 1.1.0

- After receiving feedback from initial users of this library, we've determined that the 128-bit Murmur3 hash function produces output that is too long when represented in Base36.

  The hash function has been updated to Murmur3's 32-bit variant. In order to ensure backwards compatibility for existing users, the API still outputs and receives 128-bit codes, however. Any code generated from the new version of the library will be padded to 16 bytes.
