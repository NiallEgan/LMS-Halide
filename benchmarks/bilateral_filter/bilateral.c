#include <string.h>
#include "../../testOutput/pipeline.h"


void pipeline(UCHAR * x0, UCHAR * x1, int32_t x2, int32_t x3) {
int32_t x4 = x2 * x3;
int32_t x5 = x4 * 12;
float *x1078 = malloc(sizeof(float) * x5);
for(int x8=0; x8 < x5; x8++) {
x1078[x8] = 0.0f;

}
float *x1082 = malloc(sizeof(float) * x5);
for(int x13=0; x13 < x5; x13++) {
x1082[x13] = 0.0f;

}
float *x1086 = malloc(sizeof(float) * x5);
for(int x18=0; x18 < x5; x18++) {
x1086[x18] = 0.0f;

}
float *x1090 = malloc(sizeof(float) * x5);
for(int x23=0; x23 < x5; x23++) {
x1090[x23] = 0.0f;

}
float *x1094 = malloc(sizeof(float) * x5);
for(int x28=0; x28 < x5; x28++) {
x1094[x28] = 0.0f;

}
float *x1098 = malloc(sizeof(float) * x5);
for(int x33=0; x33 < x5; x33++) {
x1098[x33] = 0.0f;

}

int sw = (x2 - 1) / 8 + 1 + 2 * 2;
int sh = (x3 - 1) / 8 + 1 + 2 * 2;

int32_t x37 = x4 * 3;
float *x1102 = malloc(sizeof(float) * x37);
int32_t x43 = x2 - 1;
int32_t x46 = x3 - 1;
for(int x40=0; x40 < x3; x40++) {
int32_t x47 = MIN(x40, x46);
int32_t x48 = MAX(0, x47);
int32_t x49 = x2 * x48;
int32_t x61 = x2 * x40;
for(int x42=0; x42 < x2; x42++) {
int32_t x44 = MIN(x42, x43);
int32_t x45 = MAX(0, x44);
int32_t x50 = x45 + x49;
int32_t x51 = 3 * x50;
int32_t x52 = x51 + 2;
UCHAR x53 = x0[x52];
float x57 = (float) x53;
float x60 = x57 * 0.00392f;
int32_t x62 = x42 + x61;
int32_t x63 = 3 * x62;
int32_t x64 = x63 + 2;
x1102[x64] = x60;
int32_t x66 = x63 + 1;
x1102[x66] = x60;
x1102[x63] = x60;

}

}
int32_t *x1110 = malloc(sizeof(int32_t) * x37);
for(int x74=0; x74 < x3; x74++) {
int32_t x82 = x74 * 8;
int32_t x102 = x2 * x74;
for(int x75=0; x75 < x2; x75++) {
int32_t x79 = x75 * 8;
int32_t x103 = x75 + x102;
int32_t x104 = 12 * x103;
for(int x77=0; x77 < 8; x77++) {
int32_t x83 = x82 + x77;
int32_t x84 = x83 - 4;
int32_t x87 = MIN(x84, x46);
int32_t x88 = MAX(0, x87);
int32_t x89 = x2 * x88;
for(int x78=0; x78 < 8; x78++) {
int32_t x80 = x79 + x78;
int32_t x81 = x80 - 4;
int32_t x85 = MIN(x81, x43);
int32_t x86 = MAX(0, x85);
int32_t x90 = x86 + x89;
int32_t x91 = 3 * x90;
int32_t x92 = x91 + 2;
float x1111 = x1102[x92];
int32_t x94 = x91 + 1;
float x1112 = x1102[x94];
float x1113 = x1102[x91];
float x1114 = MIN(x1111, 1.0f);
float x1115 = MAX(0.0f, x1114);
float x1116 = x1115 * 10.0f;
float x1117 = x1116 + 0.5f;
int32_t x1118 = (int32_t)x1117;
int32_t x1119 = x1118 + x104;
float x1120 = x1078[x1119];
float x1121 = x1120 + x1111;
x1078[x1119] = x1121;
float x1123 = x1082[x1119];
float x1124 = x1123 + 1.0f;
x1082[x1119] = x1124;

}

}
/*
for(int x116=0; x116 < 8; x116++) {
int32_t x120 = x82 + x116;
int32_t x121 = x120 - 4;
int32_t x124 = MIN(x121, x46);
int32_t x125 = MAX(0, x124);
int32_t x126 = x2 * x125;
for(int x117=0; x117 < 8; x117++) {
int32_t x118 = x79 + x117;
int32_t x119 = x118 - 4;
int32_t x122 = MIN(x119, x43);
int32_t x123 = MAX(0, x122);
int32_t x127 = x123 + x126;
int32_t x128 = 3 * x127;
int32_t x129 = x128 + 2;
float x1130 = x1102[x129];
int32_t x131 = x128 + 1;
float x1131 = x1102[x131];
float x1132 = x1102[x128];
float x1133 = MIN(x1130, 1.0f);
float x1134 = MAX(0.0f, x1133);
float x1135 = x1134 * 10.0f;
float x1136 = x1135 + 0.5f;
int32_t x1137 = (int32_t)x1136;
int32_t x1138 = x1137 + x104;
float x1139 = x1078[x1138];
float x1140 = x1139 + x1130;
x1078[x1138] = x1140;
float x1142 = x1082[x1138];
float x1143 = x1142 + 1.0f;
x1082[x1138] = x1143;

}

}*/
/*
for(int x150=0; x150 < 8; x150++) {
int32_t x154 = x82 + x150;
int32_t x155 = x154 - 4;
int32_t x158 = MIN(x155, x46);
int32_t x159 = MAX(0, x158);
int32_t x160 = x2 * x159;
for(int x151=0; x151 < 8; x151++) {
int32_t x152 = x79 + x151;
int32_t x153 = x152 - 4;
int32_t x156 = MIN(x153, x43);
int32_t x157 = MAX(0, x156);
int32_t x161 = x157 + x160;
int32_t x162 = 3 * x161;
int32_t x163 = x162 + 2;
float x1149 = x1102[x163];
int32_t x165 = x162 + 1;
float x1150 = x1102[x165];
float x1151 = x1102[x162];
float x1152 = MIN(x1149, 1.0f);
float x1153 = MAX(0.0f, x1152);
float x1154 = x1153 * 10.0f;
float x1155 = x1154 + 0.5f;
int32_t x1156 = (int32_t)x1155;
int32_t x1157 = x1156 + x104;
float x1158 = x1078[x1157];
float x1159 = x1158 + x1149;
x1078[x1157] = x1159;
float x1161 = x1082[x1157];
float x1162 = x1161 + 1.0f;
x1082[x1157] = x1162;

}

}*/
/*int32_t x184 = 3 * x103;
int32_t x185 = x184 + 2;
x1110[x185] = 0;
int32_t x187 = x184 + 1;
x1110[x187] = 0;
x1110[x184] = 0;*/

}

}
int32_t *x1175 = malloc(sizeof(int32_t) * x37);
for(int x195=1; x195 < sh-1; x195++) {
int32_t x204 = MIN(x195, x46);
int32_t x205 = MAX(0, x204);
int32_t x207 = x2 * x205;
int32_t x324 = x2 * x195;
for(int x196=1; x196 < sw-1; x196++) {
int32_t x199 = x196 - 2;
int32_t x202 = MIN(x199, x43);
int32_t x203 = MAX(0, x202);
int32_t x208 = x203 + x207;
int32_t x209 = 12 * x208;
int32_t x230 = x196 - 1;
int32_t x231 = MIN(x230, x43);
int32_t x232 = MAX(0, x231);
int32_t x233 = x232 + x207;
int32_t x234 = 12 * x233;
int32_t x254 = MIN(x196, x43);
int32_t x255 = MAX(0, x254);
int32_t x256 = x255 + x207;
int32_t x257 = 12 * x256;
int32_t x277 = x196 + 1;
int32_t x278 = MIN(x277, x43);
int32_t x279 = MAX(0, x278);
int32_t x280 = x279 + x207;
int32_t x281 = 12 * x280;
int32_t x301 = x196 + 2;
int32_t x302 = MIN(x301, x43);
int32_t x303 = MAX(0, x302);
int32_t x304 = x303 + x207;
int32_t x305 = 12 * x304;
int32_t x325 = x196 + x324;
int32_t x326 = 12 * x325;
for(int x198=0; x198 < 12; x198++) {
int32_t x200 = MIN(x198, 11);
int32_t x201 = MAX(0, x200);
int32_t x206 = x201 - 2;
int32_t x210 = x206 + x209;
float x1176 = x1078[x210];
int32_t x212 = x201 - 1;
int32_t x213 = x212 + x209;
float x1177 = x1078[x213];
int32_t x217 = x201 + x209;
float x1178 = x1078[x217];
int32_t x221 = x201 + 1;
int32_t x222 = x221 + x209;
float x1179 = x1078[x222];
int32_t x226 = x201 + 2;
int32_t x227 = x226 + x209;
float x1180 = x1078[x227];
int32_t x235 = x206 + x234;
float x1181 = x1078[x235];
int32_t x237 = x212 + x234;
float x1182 = x1078[x237];
int32_t x241 = x201 + x234;
float x1183 = x1078[x241];
int32_t x245 = x221 + x234;
float x1184 = x1078[x245];
int32_t x249 = x226 + x234;
float x1185 = x1078[x249];
int32_t x258 = x206 + x257;
float x1186 = x1078[x258];
int32_t x260 = x212 + x257;
float x1187 = x1078[x260];
int32_t x264 = x201 + x257;
float x1188 = x1078[x264];
int32_t x268 = x221 + x257;
float x1189 = x1078[x268];
int32_t x272 = x226 + x257;
float x1190 = x1078[x272];
int32_t x282 = x206 + x281;
float x1191 = x1078[x282];
int32_t x284 = x212 + x281;
float x1192 = x1078[x284];
int32_t x288 = x201 + x281;
float x1193 = x1078[x288];
int32_t x292 = x221 + x281;
float x1194 = x1078[x292];
int32_t x296 = x226 + x281;
float x1195 = x1078[x296];
int32_t x306 = x206 + x305;
float x1196 = x1078[x306];
int32_t x308 = x212 + x305;
float x1197 = x1078[x308];
int32_t x312 = x201 + x305;
float x1198 = x1078[x312];
int32_t x316 = x221 + x305;
float x1199 = x1078[x316];
int32_t x320 = x226 + x305;
float x1200 = x1078[x320];
int32_t x327 = x198 + x326;
float x1201 = x1177 * 4.0f;
float x1202 = x1176 + x1201;
float x1203 = x1178 * 6.0f;
float x1204 = x1202 + x1203;
float x1205 = x1179 * 4.0f;
float x1206 = x1204 + x1205;
float x1207 = x1206 + x1180;
float x1208 = x1182 * 4.0f;
float x1209 = x1181 + x1208;
float x1210 = x1183 * 6.0f;
float x1211 = x1209 + x1210;
float x1212 = x1184 * 4.0f;
float x1213 = x1211 + x1212;
float x1214 = x1213 + x1185;
float x1215 = x1214 * 4.0f;
float x1216 = x1207 + x1215;
float x1217 = x1187 * 4.0f;
float x1218 = x1186 + x1217;
float x1219 = x1188 * 6.0f;
float x1220 = x1218 + x1219;
float x1221 = x1189 * 4.0f;
float x1222 = x1220 + x1221;
float x1223 = x1222 + x1190;
float x1224 = x1223 * 6.0f;
float x1225 = x1216 + x1224;
float x1226 = x1192 * 4.0f;
float x1227 = x1191 + x1226;
float x1228 = x1193 * 6.0f;
float x1229 = x1227 + x1228;
float x1230 = x1194 * 4.0f;
float x1231 = x1229 + x1230;
float x1232 = x1231 + x1195;
float x1233 = x1232 * 4.0f;
float x1234 = x1225 + x1233;
float x1235 = x1197 * 4.0f;
float x1236 = x1196 + x1235;
float x1237 = x1198 * 6.0f;
float x1238 = x1236 + x1237;
float x1239 = x1199 * 4.0f;
float x1240 = x1238 + x1239;
float x1241 = x1240 + x1200;
float x1242 = x1234 + x1241;
x1086[x327] = x1242;

}
for(int x331=0; x331 < 12; x331++) {
int32_t x332 = MIN(x331, 11);
int32_t x333 = MAX(0, x332);
int32_t x334 = x333 - 2;
int32_t x335 = x334 + x209;
float x1246 = x1082[x335];
int32_t x337 = x333 - 1;
int32_t x338 = x337 + x209;
float x1247 = x1082[x338];
int32_t x342 = x333 + x209;
float x1248 = x1082[x342];
int32_t x346 = x333 + 1;
int32_t x347 = x346 + x209;
float x1249 = x1082[x347];
int32_t x351 = x333 + 2;
int32_t x352 = x351 + x209;
float x1250 = x1082[x352];
int32_t x355 = x334 + x234;
float x1251 = x1082[x355];
int32_t x357 = x337 + x234;
float x1252 = x1082[x357];
int32_t x361 = x333 + x234;
float x1253 = x1082[x361];
int32_t x365 = x346 + x234;
float x1254 = x1082[x365];
int32_t x369 = x351 + x234;
float x1255 = x1082[x369];
int32_t x374 = x334 + x257;
float x1256 = x1082[x374];
int32_t x376 = x337 + x257;
float x1257 = x1082[x376];
int32_t x380 = x333 + x257;
float x1258 = x1082[x380];
int32_t x384 = x346 + x257;
float x1259 = x1082[x384];
int32_t x388 = x351 + x257;
float x1260 = x1082[x388];
int32_t x393 = x334 + x281;
float x1261 = x1082[x393];
int32_t x395 = x337 + x281;
float x1262 = x1082[x395];
int32_t x399 = x333 + x281;
float x1263 = x1082[x399];
int32_t x403 = x346 + x281;
float x1264 = x1082[x403];
int32_t x407 = x351 + x281;
float x1265 = x1082[x407];
int32_t x412 = x334 + x305;
float x1266 = x1082[x412];
int32_t x414 = x337 + x305;
float x1267 = x1082[x414];
int32_t x418 = x333 + x305;
float x1268 = x1082[x418];
int32_t x422 = x346 + x305;
float x1269 = x1082[x422];
int32_t x426 = x351 + x305;
float x1270 = x1082[x426];
int32_t x430 = x331 + x326;
float x1271 = x1247 * 4.0f;
float x1272 = x1246 + x1271;
float x1273 = x1248 * 6.0f;
float x1274 = x1272 + x1273;
float x1275 = x1249 * 4.0f;
float x1276 = x1274 + x1275;
float x1277 = x1276 + x1250;
float x1278 = x1252 * 4.0f;
float x1279 = x1251 + x1278;
float x1280 = x1253 * 6.0f;
float x1281 = x1279 + x1280;
float x1282 = x1254 * 4.0f;
float x1283 = x1281 + x1282;
float x1284 = x1283 + x1255;
float x1285 = x1284 * 4.0f;
float x1286 = x1277 + x1285;
float x1287 = x1257 * 4.0f;
float x1288 = x1256 + x1287;
float x1289 = x1258 * 6.0f;
float x1290 = x1288 + x1289;
float x1291 = x1259 * 4.0f;
float x1292 = x1290 + x1291;
float x1293 = x1292 + x1260;
float x1294 = x1293 * 6.0f;
float x1295 = x1286 + x1294;
float x1296 = x1262 * 4.0f;
float x1297 = x1261 + x1296;
float x1298 = x1263 * 6.0f;
float x1299 = x1297 + x1298;
float x1300 = x1264 * 4.0f;
float x1301 = x1299 + x1300;
float x1302 = x1301 + x1265;
float x1303 = x1302 * 4.0f;
float x1304 = x1295 + x1303;
float x1305 = x1267 * 4.0f;
float x1306 = x1266 + x1305;
float x1307 = x1268 * 6.0f;
float x1308 = x1306 + x1307;
float x1309 = x1269 * 4.0f;
float x1310 = x1308 + x1309;
float x1311 = x1310 + x1270;
float x1312 = x1304 + x1311;
x1090[x430] = x1312;

}
/*int32_t x434 = 3 * x325;
int32_t x435 = x434 + 2;
x1175[x435] = 0;
int32_t x437 = x434 + 1;
x1175[x437] = 0;
x1175[x434] = 0;*/

}

}
int32_t *x1323 = malloc(sizeof(int32_t) * x37);
for(int x445=1; x445 < sh-1; x445++) {
int32_t x453 = MIN(x445, x46);
int32_t x454 = MAX(0, x453);
int32_t x456 = x2 * x454;
int32_t x573 = x2 * x445;
for(int x446=1; x446 < sw-1; x446++) {
int32_t x448 = x446 - 2;
int32_t x451 = MIN(x448, x43);
int32_t x452 = MAX(0, x451);
int32_t x457 = x452 + x456;
int32_t x458 = 12 * x457;
int32_t x479 = x446 - 1;
int32_t x480 = MIN(x479, x43);
int32_t x481 = MAX(0, x480);
int32_t x482 = x481 + x456;
int32_t x483 = 12 * x482;
int32_t x503 = MIN(x446, x43);
int32_t x504 = MAX(0, x503);
int32_t x505 = x504 + x456;
int32_t x506 = 12 * x505;
int32_t x526 = x446 + 1;
int32_t x527 = MIN(x526, x43);
int32_t x528 = MAX(0, x527);
int32_t x529 = x528 + x456;
int32_t x530 = 12 * x529;
int32_t x550 = x446 + 2;
int32_t x551 = MIN(x550, x43);
int32_t x552 = MAX(0, x551);
int32_t x553 = x552 + x456;
int32_t x554 = 12 * x553;
int32_t x574 = x446 + x573;
int32_t x575 = 12 * x574;
for(int x447=0; x447 < 12; x447++) {
int32_t x449 = MIN(x447, 11);
int32_t x450 = MAX(0, x449);
int32_t x455 = x450 - 2;
int32_t x459 = x455 + x458;
float x1324 = x1078[x459];
int32_t x461 = x450 - 1;
int32_t x462 = x461 + x458;
float x1325 = x1078[x462];
int32_t x466 = x450 + x458;
float x1326 = x1078[x466];
int32_t x470 = x450 + 1;
int32_t x471 = x470 + x458;
float x1327 = x1078[x471];
int32_t x475 = x450 + 2;
int32_t x476 = x475 + x458;
float x1328 = x1078[x476];
int32_t x484 = x455 + x483;
float x1329 = x1078[x484];
int32_t x486 = x461 + x483;
float x1330 = x1078[x486];
int32_t x490 = x450 + x483;
float x1331 = x1078[x490];
int32_t x494 = x470 + x483;
float x1332 = x1078[x494];
int32_t x498 = x475 + x483;
float x1333 = x1078[x498];
int32_t x507 = x455 + x506;
float x1334 = x1078[x507];
int32_t x509 = x461 + x506;
float x1335 = x1078[x509];
int32_t x513 = x450 + x506;
float x1336 = x1078[x513];
int32_t x517 = x470 + x506;
float x1337 = x1078[x517];
int32_t x521 = x475 + x506;
float x1338 = x1078[x521];
int32_t x531 = x455 + x530;
float x1339 = x1078[x531];
int32_t x533 = x461 + x530;
float x1340 = x1078[x533];
int32_t x537 = x450 + x530;
float x1341 = x1078[x537];
int32_t x541 = x470 + x530;
float x1342 = x1078[x541];
int32_t x545 = x475 + x530;
float x1343 = x1078[x545];
int32_t x555 = x455 + x554;
float x1344 = x1078[x555];
int32_t x557 = x461 + x554;
float x1345 = x1078[x557];
int32_t x561 = x450 + x554;
float x1346 = x1078[x561];
int32_t x565 = x470 + x554;
float x1347 = x1078[x565];
int32_t x569 = x475 + x554;
float x1348 = x1078[x569];
int32_t x576 = x447 + x575;
float x1349 = x1325 * 4.0f;
float x1350 = x1324 + x1349;
float x1351 = x1326 * 6.0f;
float x1352 = x1350 + x1351;
float x1353 = x1327 * 4.0f;
float x1354 = x1352 + x1353;
float x1355 = x1354 + x1328;
float x1356 = x1330 * 4.0f;
float x1357 = x1329 + x1356;
float x1358 = x1331 * 6.0f;
float x1359 = x1357 + x1358;
float x1360 = x1332 * 4.0f;
float x1361 = x1359 + x1360;
float x1362 = x1361 + x1333;
float x1363 = x1362 * 4.0f;
float x1364 = x1355 + x1363;
float x1365 = x1335 * 4.0f;
float x1366 = x1334 + x1365;
float x1367 = x1336 * 6.0f;
float x1368 = x1366 + x1367;
float x1369 = x1337 * 4.0f;
float x1370 = x1368 + x1369;
float x1371 = x1370 + x1338;
float x1372 = x1371 * 6.0f;
float x1373 = x1364 + x1372;
float x1374 = x1340 * 4.0f;
float x1375 = x1339 + x1374;
float x1376 = x1341 * 6.0f;
float x1377 = x1375 + x1376;
float x1378 = x1342 * 4.0f;
float x1379 = x1377 + x1378;
float x1380 = x1379 + x1343;
float x1381 = x1380 * 4.0f;
float x1382 = x1373 + x1381;
float x1383 = x1345 * 4.0f;
float x1384 = x1344 + x1383;
float x1385 = x1346 * 6.0f;
float x1386 = x1384 + x1385;
float x1387 = x1347 * 4.0f;
float x1388 = x1386 + x1387;
float x1389 = x1388 + x1348;
float x1390 = x1382 + x1389;
x1086[x576] = x1390;

}
for(int x580=0; x580 < 12; x580++) {
int32_t x581 = MIN(x580, 11);
int32_t x582 = MAX(0, x581);
int32_t x583 = x582 - 2;
int32_t x584 = x583 + x458;
float x1394 = x1082[x584];
int32_t x586 = x582 - 1;
int32_t x587 = x586 + x458;
float x1395 = x1082[x587];
int32_t x591 = x582 + x458;
float x1396 = x1082[x591];
int32_t x595 = x582 + 1;
int32_t x596 = x595 + x458;
float x1397 = x1082[x596];
int32_t x600 = x582 + 2;
int32_t x601 = x600 + x458;
float x1398 = x1082[x601];
int32_t x604 = x583 + x483;
float x1399 = x1082[x604];
int32_t x606 = x586 + x483;
float x1400 = x1082[x606];
int32_t x610 = x582 + x483;
float x1401 = x1082[x610];
int32_t x614 = x595 + x483;
float x1402 = x1082[x614];
int32_t x618 = x600 + x483;
float x1403 = x1082[x618];
int32_t x623 = x583 + x506;
float x1404 = x1082[x623];
int32_t x625 = x586 + x506;
float x1405 = x1082[x625];
int32_t x629 = x582 + x506;
float x1406 = x1082[x629];
int32_t x633 = x595 + x506;
float x1407 = x1082[x633];
int32_t x637 = x600 + x506;
float x1408 = x1082[x637];
int32_t x642 = x583 + x530;
float x1409 = x1082[x642];
int32_t x644 = x586 + x530;
float x1410 = x1082[x644];
int32_t x648 = x582 + x530;
float x1411 = x1082[x648];
int32_t x652 = x595 + x530;
float x1412 = x1082[x652];
int32_t x656 = x600 + x530;
float x1413 = x1082[x656];
int32_t x661 = x583 + x554;
float x1414 = x1082[x661];
int32_t x663 = x586 + x554;
float x1415 = x1082[x663];
int32_t x667 = x582 + x554;
float x1416 = x1082[x667];
int32_t x671 = x595 + x554;
float x1417 = x1082[x671];
int32_t x675 = x600 + x554;
float x1418 = x1082[x675];
int32_t x679 = x580 + x575;
float x1419 = x1395 * 4.0f;
float x1420 = x1394 + x1419;
float x1421 = x1396 * 6.0f;
float x1422 = x1420 + x1421;
float x1423 = x1397 * 4.0f;
float x1424 = x1422 + x1423;
float x1425 = x1424 + x1398;
float x1426 = x1400 * 4.0f;
float x1427 = x1399 + x1426;
float x1428 = x1401 * 6.0f;
float x1429 = x1427 + x1428;
float x1430 = x1402 * 4.0f;
float x1431 = x1429 + x1430;
float x1432 = x1431 + x1403;
float x1433 = x1432 * 4.0f;
float x1434 = x1425 + x1433;
float x1435 = x1405 * 4.0f;
float x1436 = x1404 + x1435;
float x1437 = x1406 * 6.0f;
float x1438 = x1436 + x1437;
float x1439 = x1407 * 4.0f;
float x1440 = x1438 + x1439;
float x1441 = x1440 + x1408;
float x1442 = x1441 * 6.0f;
float x1443 = x1434 + x1442;
float x1444 = x1410 * 4.0f;
float x1445 = x1409 + x1444;
float x1446 = x1411 * 6.0f;
float x1447 = x1445 + x1446;
float x1448 = x1412 * 4.0f;
float x1449 = x1447 + x1448;
float x1450 = x1449 + x1413;
float x1451 = x1450 * 4.0f;
float x1452 = x1443 + x1451;
float x1453 = x1415 * 4.0f;
float x1454 = x1414 + x1453;
float x1455 = x1416 * 6.0f;
float x1456 = x1454 + x1455;
float x1457 = x1417 * 4.0f;
float x1458 = x1456 + x1457;
float x1459 = x1458 + x1418;
float x1460 = x1452 + x1459;
x1090[x679] = x1460;

}
/*int32_t x683 = 3 * x574;
int32_t x684 = x683 + 2;
x1323[x684] = 0;
int32_t x686 = x683 + 1;
x1323[x686] = 0;
x1323[x683] = 0;*/

}

}
int32_t *x1471 = malloc(sizeof(int32_t) * x37);
for(int x694=0; x694 < sh-1; x694++) {
int32_t x697 = x694 - 2;
int32_t x698 = x2 * x697;
int32_t x703 = x694 - 1;
int32_t x704 = x2 * x703;
int32_t x711 = x2 * x694;
int32_t x718 = x694 + 1;
int32_t x719 = x2 * x718;
int32_t x726 = x694 + 2;
int32_t x727 = x2 * x726;
for(int x695=0; x695 < sw-1; x695++) {
int32_t x699 = x695 + x698;
int32_t x700 = 12 * x699;
int32_t x705 = x695 + x704;
int32_t x706 = 12 * x705;
int32_t x712 = x695 + x711;
int32_t x713 = 12 * x712;
int32_t x720 = x695 + x719;
int32_t x721 = 12 * x720;
int32_t x728 = x695 + x727;
int32_t x729 = 12 * x728;
for(int x696=0; x696 < 12; x696++) {
int32_t x701 = x696 + x700;
float x1472 = x1086[x701];
int32_t x707 = x696 + x706;
float x1473 = x1086[x707];
int32_t x714 = x696 + x713;
float x1474 = x1086[x714];
int32_t x722 = x696 + x721;
float x1475 = x1086[x722];
int32_t x730 = x696 + x729;
float x1476 = x1086[x730];
float x1477 = x1473 * 4.0f;
float x1478 = x1472 + x1477;
float x1479 = x1474 * 6.0f;
float x1480 = x1478 + x1479;
float x1481 = x1475 * 4.0f;
float x1482 = x1480 + x1481;
float x1483 = x1482 + x1476;
x1094[x714] = x1483;

}
for(int x736=0; x736 < 12; x736++) {
int32_t x737 = x736 + x700;
float x1487 = x1090[x737];
int32_t x739 = x736 + x706;
float x1488 = x1090[x739];
int32_t x743 = x736 + x713;
float x1489 = x1090[x743];
int32_t x747 = x736 + x721;
float x1490 = x1090[x747];
int32_t x751 = x736 + x729;
float x1491 = x1090[x751];
float x1492 = x1488 * 4.0f;
float x1493 = x1487 + x1492;
float x1494 = x1489 * 6.0f;
float x1495 = x1493 + x1494;
float x1496 = x1490 * 4.0f;
float x1497 = x1495 + x1496;
float x1498 = x1497 + x1491;
x1098[x743] = x1498;

}
/*int32_t x757 = 3 * x712;
int32_t x758 = x757 + 2;
x1471[x758] = 0;
int32_t x760 = x757 + 1;
x1471[x760] = 0;
x1471[x757] = 0;*/

}

}
int32_t *x1509 = malloc(sizeof(int32_t) * x37);
for(int x768=1; x768 < sh-1; x768++) {
int32_t x771 = x768 - 2;
int32_t x772 = x2 * x771;
int32_t x777 = x768 - 1;
int32_t x778 = x2 * x777;
int32_t x785 = x2 * x768;
int32_t x792 = x768 + 1;
int32_t x793 = x2 * x792;
int32_t x800 = x768 + 2;
int32_t x801 = x2 * x800;
for(int x769=1; x769 < sw-1; x769++) {
int32_t x773 = x769 + x772;
int32_t x774 = 12 * x773;
int32_t x779 = x769 + x778;
int32_t x780 = 12 * x779;
int32_t x786 = x769 + x785;
int32_t x787 = 12 * x786;
int32_t x794 = x769 + x793;
int32_t x795 = 12 * x794;
int32_t x802 = x769 + x801;
int32_t x803 = 12 * x802;
for(int x770=0; x770 < 12; x770++) {
int32_t x775 = x770 + x774;
float x1510 = x1086[x775];
int32_t x781 = x770 + x780;
float x1511 = x1086[x781];
int32_t x788 = x770 + x787;
float x1512 = x1086[x788];
int32_t x796 = x770 + x795;
float x1513 = x1086[x796];
int32_t x804 = x770 + x803;
float x1514 = x1086[x804];
float x1515 = x1511 * 4.0f;
float x1516 = x1510 + x1515;
float x1517 = x1512 * 6.0f;
float x1518 = x1516 + x1517;
float x1519 = x1513 * 4.0f;
float x1520 = x1518 + x1519;
float x1521 = x1520 + x1514;
x1094[x788] = x1521;

}
for(int x810=0; x810 < 12; x810++) {
int32_t x811 = x810 + x774;
float x1525 = x1090[x811];
int32_t x813 = x810 + x780;
float x1526 = x1090[x813];
int32_t x817 = x810 + x787;
float x1527 = x1090[x817];
int32_t x821 = x810 + x795;
float x1528 = x1090[x821];
int32_t x825 = x810 + x803;
float x1529 = x1090[x825];
float x1530 = x1526 * 4.0f;
float x1531 = x1525 + x1530;
float x1532 = x1527 * 6.0f;
float x1533 = x1531 + x1532;
float x1534 = x1528 * 4.0f;
float x1535 = x1533 + x1534;
float x1536 = x1535 + x1529;
x1098[x817] = x1536;

}
/*int32_t x831 = 3 * x786;
int32_t x832 = x831 + 2;
x1509[x832] = 0;
int32_t x834 = x831 + 1;
x1509[x834] = 0;
x1509[x831] = 0;*/

}

}
float *x1547 = malloc(sizeof(float) * x37);
for(int x842=0; x842 < x3; x842++) {
int32_t x846 = MIN(x842, x46);
int32_t x847 = MAX(0, x846);
int32_t x848 = x2 * x847;
int32_t x867 = x842 / 8;
int32_t x868 = x2 * x867;
int32_t x882 = x867 + 1;
int32_t x883 = x2 * x882;
int32_t x921 = x2 * x842;
int32_t x863 = x842 % 8;
float x864 = (float)x863;
float x865 = x864 / 8.0f;
float x895 = 1.0f - x865;
for(int x843=0; x843 < x2; x843++) {
int32_t x844 = MIN(x843, x43);
int32_t x845 = MAX(0, x844);
int32_t x849 = x845 + x848;
int32_t x850 = 3 * x849;
int32_t x851 = x850 + 2;
float x1548 = x1102[x851];
int32_t x853 = x850 + 1;
float x1549 = x1102[x853];
float x1550 = x1102[x850];
int32_t x866 = x843 / 8;
int32_t x869 = x866 + x868;
int32_t x870 = 12 * x869;
float x1551 = x1548 * 10.0f;
int32_t x1552 = (int32_t)x1551;
int32_t x1553 = x1552 + x870;
float x1554 = x1094[x1553];
int32_t x873 = x866 + 1;
int32_t x874 = x873 + x868;
int32_t x875 = 12 * x874;
int32_t x1555 = x1552 + x875;
float x1556 = x1094[x1555];
int32_t x884 = x866 + x883;
int32_t x885 = 12 * x884;
int32_t x1557 = x1552 + x885;
float x1558 = x1094[x1557];
int32_t x888 = x873 + x883;
int32_t x889 = 12 * x888;
int32_t x1559 = x1552 + x889;
float x1560 = x1094[x1559];
int32_t x1561 = x1552 + 1;
int32_t x1562 = x1561 + x870;
float x1563 = x1094[x1562];
int32_t x1564 = x1561 + x875;
float x1565 = x1094[x1564];
int32_t x1566 = x1561 + x885;
float x1567 = x1094[x1566];
int32_t x1568 = x1561 + x889;
float x1569 = x1094[x1568];
int32_t x922 = x843 + x921;
int32_t x923 = 3 * x922;
int32_t x924 = x923 + 2;
int32_t x860 = x843 % 8;
float x861 = (float)x860;
float x862 = x861 / 8.0f;
float x878 = 1.0f - x862;
float x1570 = x1554 * x878;
float x1571 = x1556 * x862;
float x1572 = x1570 + x1571;
float x1573 = x1572 * x895;
float x1574 = x1558 * x878;
float x1575 = x1560 * x862;
float x1576 = x1574 + x1575;
float x1577 = x1576 * x865;
float x1578 = x1573 + x1577;
float x1579 = (float)x1552;
float x1580 = x1551 - x1579;
float x1581 = 1.0f - x1580;
float x1582 = x1578 * x1581;
float x1583 = x1563 * x878;
float x1584 = x1565 * x862;
float x1585 = x1583 + x1584;
float x1586 = x1585 * x895;
float x1587 = x1567 * x878;
float x1588 = x1569 * x862;
float x1589 = x1587 + x1588;
float x1590 = x1589 * x865;
float x1591 = x1586 + x1590;
float x1592 = x1591 * x1580;
float x1593 = x1582 + x1592;
x1547[x924] = x1593;
int32_t x926 = x923 + 1;
x1547[x926] = x1593;
x1547[x923] = x1593;

}

}
float *x1601 = malloc(sizeof(float) * x37);
for(int x934=0; x934 < x3; x934++) {
int32_t x938 = MIN(x934, x46);
int32_t x939 = MAX(0, x938);
int32_t x940 = x2 * x939;
int32_t x959 = x934 / 8;
int32_t x960 = x2 * x959;
int32_t x974 = x959 + 1;
int32_t x975 = x2 * x974;
int32_t x1013 = x2 * x934;
int32_t x955 = x934 % 8;
float x956 = (float)x955;
float x957 = x956 / 8.0f;
float x987 = 1.0f - x957;
for(int x935=0; x935 < x2; x935++) {
int32_t x936 = MIN(x935, x43);
int32_t x937 = MAX(0, x936);
int32_t x941 = x937 + x940;
int32_t x942 = 3 * x941;
int32_t x943 = x942 + 2;
float x1602 = x1102[x943];
int32_t x945 = x942 + 1;
float x1603 = x1102[x945];
float x1604 = x1102[x942];
int32_t x958 = x935 / 8;
int32_t x961 = x958 + x960;
int32_t x962 = 12 * x961;
float x1605 = x1602 * 10.0f;
int32_t x1606 = (int32_t)x1605;
int32_t x1607 = x1606 + x962;
float x1608 = x1098[x1607];
int32_t x965 = x958 + 1;
int32_t x966 = x965 + x960;
int32_t x967 = 12 * x966;
int32_t x1609 = x1606 + x967;
float x1610 = x1098[x1609];
int32_t x976 = x958 + x975;
int32_t x977 = 12 * x976;
int32_t x1611 = x1606 + x977;
float x1612 = x1098[x1611];
int32_t x980 = x965 + x975;
int32_t x981 = 12 * x980;
int32_t x1613 = x1606 + x981;
float x1614 = x1098[x1613];
int32_t x1615 = x1606 + 1;
int32_t x1616 = x1615 + x962;
float x1617 = x1098[x1616];
int32_t x1618 = x1615 + x967;
float x1619 = x1098[x1618];
int32_t x1620 = x1615 + x977;
float x1621 = x1098[x1620];
int32_t x1622 = x1615 + x981;
float x1623 = x1098[x1622];
int32_t x1014 = x935 + x1013;
int32_t x1015 = 3 * x1014;
int32_t x1016 = x1015 + 2;
int32_t x952 = x935 % 8;
float x953 = (float)x952;
float x954 = x953 / 8.0f;
float x970 = 1.0f - x954;
float x1624 = x1608 * x970;
float x1625 = x1610 * x954;
float x1626 = x1624 + x1625;
float x1627 = x1626 * x987;
float x1628 = x1612 * x970;
float x1629 = x1614 * x954;
float x1630 = x1628 + x1629;
float x1631 = x1630 * x957;
float x1632 = x1627 + x1631;
float x1633 = (float)x1606;
float x1634 = x1605 - x1633;
float x1635 = 1.0f - x1634;
float x1636 = x1632 * x1635;
float x1637 = x1617 * x970;
float x1638 = x1619 * x954;
float x1639 = x1637 + x1638;
float x1640 = x1639 * x987;
float x1641 = x1621 * x970;
float x1642 = x1623 * x954;
float x1643 = x1641 + x1642;
float x1644 = x1643 * x957;
float x1645 = x1640 + x1644;
float x1646 = x1645 * x1634;
float x1647 = x1636 + x1646;
x1601[x1016] = x1647;
int32_t x1018 = x1015 + 1;
x1601[x1018] = x1647;
x1601[x1015] = x1647;

}

}
UCHAR *x1655 = malloc(sizeof(UCHAR) * x37);
for(int x1026=0; x1026 < x3; x1026++) {
int32_t x1030 = MIN(x1026, x46);
int32_t x1031 = MAX(0, x1030);
int32_t x1032 = x2 * x1031;
int32_t x1055 = x2 * x1026;
for(int x1027=0; x1027 < x2; x1027++) {
int32_t x1028 = MIN(x1027, x43);
int32_t x1029 = MAX(0, x1028);
int32_t x1033 = x1029 + x1032;
int32_t x1034 = 3 * x1033;
int32_t x1035 = x1034 + 2;
float x1656 = x1547[x1035];
int32_t x1037 = x1034 + 1;
float x1657 = x1547[x1037];
float x1658 = x1547[x1034];
float x1659 = x1601[x1035];
float x1660 = x1601[x1037];
float x1661 = x1601[x1034];
int32_t x1056 = x1027 + x1055;
int32_t x1057 = 3 * x1056;
int32_t x1058 = x1057 + 2;
float x1662 = x1656 / x1659;
float x1663 = 255.0f * x1662;
float x1664 = x1663 + 0.5f;
UCHAR x1665 = (UCHAR) x1664;
x1655[x1058] = x1665;
int32_t x1060 = x1057 + 1;
float x1667 = x1657 / x1660;
float x1668 = 255.0f * x1667;
float x1669 = x1668 + 0.5f;
UCHAR x1670 = (UCHAR) x1669;
x1655[x1060] = x1670;
float x1672 = x1658 / x1661;
float x1673 = 255.0f * x1672;
float x1674 = x1673 + 0.5f;
UCHAR x1675 = (UCHAR) x1674;
x1655[x1057] = x1675;

}

}
free(x1601);
free(x1547);
free(x1509);
free(x1471);
free(x1323);
free(x1175);
free(x1110);
free(x1102);
memcpy(x1, x1655, x37);
free(x1655);
}
int32_t WIDTH_OUT_DIFF = 0;
int32_t HEIGHT_OUT_DIFF = 0;
int32_t WIDTH_OUT_MUL = 1;
int32_t WIDTH_OUT_DIV = 1;
int32_t HEIGHT_OUT_MUL = 1;
int32_t HEIGHT_OUT_DIV = 1;
