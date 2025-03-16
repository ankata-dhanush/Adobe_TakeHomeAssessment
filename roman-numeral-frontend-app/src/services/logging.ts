/* eslint-disable @typescript-eslint/no-explicit-any */
/* 
  App wide logging util.
  Right now, this just wraps console.log, in the future we can add
  log filtering, levels, centralized logging, etc. Prefer this over
  using console directly.
*/

import { SeverityLevel } from "../enums/SeverityLevel";
import { trackTrace } from "./monitoring";

const paramsToString = (params: any[]) => { 
  return params.map((p) => JSON.stringify(p)).join(", ");
};

export const Logger = {
  trace: (message?: any, ...optionalParams: any[]) => {
    console.log(message, ...optionalParams);
    trackTrace(
      `${message} ${paramsToString(optionalParams)}`,
      SeverityLevel.TRACE
    );
  },

  log: (message?: any, ...optionalParams: any[]) => {
    console.log(message, ...optionalParams);
    trackTrace(
      `${message} ${paramsToString(optionalParams)}`,
      SeverityLevel.LOG
    );
  },

  info: (message?: any, ...optionalParams: any[]) => {
    console.info(message, ...optionalParams);
    trackTrace(
      `${message} ${paramsToString(optionalParams)}`,
      SeverityLevel.INFO
    );
  },

  warn: (message?: any, ...optionalParams: any[]) => {
    console.warn(message, ...optionalParams);
    trackTrace(
      `${message} ${paramsToString(optionalParams)}`,
      SeverityLevel.WARN
    );
  },

  error: (message?: any, ...optionalParams: any[]) => {
    console.error(message, ...optionalParams);
    trackTrace(
      `${message} ${paramsToString(optionalParams)}`,
      SeverityLevel.ERROR
    );
  },
};
